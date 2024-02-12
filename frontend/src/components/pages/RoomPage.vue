<script setup lang="ts">
import NavBar from '@/components/organisms/common/NavBar.vue'
import ChatRoom from '@/components/organisms/game/ChatRoom.vue'
import type { ChatRoomType } from '@/types/chat'
import type { RoomUserInfoType, RoomInfoType } from '@/types/room'
import { getRoom } from '@/api/room'
import { useMenuStore } from '@/stores/menu'
import { SSEConnect } from '@/api/sse'
import { getNotificationLists } from '@/api/notification'
import { onMounted, onUnmounted, ref, provide, readonly } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { storeToRefs } from 'pinia'

const router = useRouter()
const route = useRoute()
const menuStore = useMenuStore()
const emit = defineEmits(['update-name', 'in-room', 'set-nickname'])

const { menuSeen, isMobile } = storeToRefs(menuStore)
const notificationLists = ref([])
const entryCode = ref('')
const chatRooms = ref<ChatRoomType[]>([])
//라우터로 부터 방번호를 받아온다
let eventSource: EventSource

const roomUserInfo = ref<RoomUserInfoType>({
    roomNo: Number(route.params.roomNo),
    roomUserNo: 0,
    roomName: '',
    roomNickname: '',
    profileUrl: ''
})
const roomInfo = ref<RoomInfoType>()
const roomUserNo = ref<number>(-1)
const roomNo = ref<number>(Number(route.params.roomNo))
const hostRoomUserNo = ref<number>(-1)

const updateRoomName = (name: string | undefined) => {
    roomUserInfo.value.roomName = name ? name : '방 제목'
    console.log('sibling', roomUserInfo.value.roomName)
    emit('update-name', roomUserInfo.value.roomName)
}

provide('roomUserInfo', readonly(roomUserInfo))
provide('roomUserNo', readonly(roomUserNo))
provide('hostRoomUserNo', readonly(hostRoomUserNo))
provide('roomCode', readonly(entryCode))
provide('notifyLists', readonly(notificationLists))
provide('roomNo', readonly(roomNo))

const removeChatRoom = (name: string) => {
    const index = chatRooms.value.findIndex((room) => room.name === name)
    if (index !== -1) {
        chatRooms.value.splice(index, 1)
    }
}

const makeRoom = ({ name, imageUrl }: ChatRoomType) => {
    // 이미 만들어진 채팅 방인지 체크
    const existingRoom = chatRooms.value.find((room) => room.name === name)
    if (!existingRoom) {
        chatRooms.value.push({
            name: name,
            imageUrl: imageUrl
        })
    }
}

const SSEConnection = (roomUserNo: number) => {
    console.log('SSEConnection', roomUserNo)
    eventSource = SSEConnect(roomUserNo)

    eventSource.onopen = () => {
        console.log('Server Sent Event 연결이 열렸습니다.')
    }

    // 서버로부터 알림 메시지가 오면 적절한 처리 로직을 수행
    eventSource.addEventListener('message', (event) => {
        const data = JSON.parse(event.data)
        alert(data.author + '으로부터 ' + data.content + '도착!')
        // router.go(0)
    })

    eventSource.addEventListener('error', (event) => {
        console.error('Server Sent Event error:', event)
    })
}

const getRoomData = () => {
    console.log('방정보를 호출')
    getRoom(
        roomUserInfo.value.roomNo,
        ({ data }) => {
            console.table(data)
            console.table(data.result)
            roomInfo.value = data.result
            roomUserInfo.value.profileUrl = data.result.userInfo.profileUrl
            roomUserInfo.value.roomNickname = data.result.userInfo.nickname
            roomUserInfo.value.roomUserNo = data.result.userInfo.roomUserNo
            roomUserInfo.value.roomName = data.result.roomName
            roomUserNo.value = data.result.userInfo.roomUserNo
            hostRoomUserNo.value = data.result.hostRoomUserNo
            entryCode.value = data.result.entryCode
            updateRoomName(data.result.roomName)
            emit('set-nickname', data.result.userInfo.nickname)
            SSEConnection(data.result.userInfo.roomUserNo)
        },
        (error) => {
            alert(error.response.data.message)
            router.replace('/')
        }
    )
}

const getNotify = () => {
    getNotificationLists(
        Number(route.params.roomNo),
        ({ data }) => {
            notificationLists.value = data.result
            console.log('알람리스트', notificationLists.value)
        },
        (error) => {
            console.log(error)
        }
    )
}

onMounted(() => {
    console.log('in-room은 true로 변경합니다.')
    getRoomData()
    getNotify()
    emit('in-room', true)
})

onUnmounted(() => {
    if (eventSource) {
        console.log('SSE 연결을 종료합니다.')
        eventSource.close()
    }
    console.log('in-room은 false로 변경합니다.')
    emit('in-room', false)
})
</script>

<template>
    <div class="flex flex-1 bg-A805RealWhite">
        <div v-for="room in chatRooms" :key="room.name">
            <ChatRoom :name="room.name" :imageUrl="room.imageUrl" @close-chat-room="removeChatRoom" />
        </div>
        <!-- pc버전이거나, 모바일 버전 + 메뉴가 체크된 상태일때만 nav가 보인다. -->
        <NavBar @make-room="makeRoom" v-if="!isMobile || menuSeen" :room-name="roomUserInfo.roomName"
            :room-info="roomInfo" />
        <!-- pc버전이거나, 모바일 버전 + 메뉴가 닫힌 상태일때만 이 영역 이 보인다. -->
        <RouterView
            v-if="!isMobile || !menuSeen"
            :room-info="roomInfo"
            @refresh-notify="getNotify"
            @room-name-changed="updateRoomName"
        />
    </div>
</template>

<style scoped></style>
