<script setup lang="ts">
import NavBar from '@/components/organisms/common/NavBar.vue'
import ChatRoom from '@/components/organisms/game/ChatRoom.vue'
import { onMounted, onUnmounted, ref } from 'vue'
import type { ChatRoomType } from '@/types/chat'
import { getRoom } from '@/api/room'
import { useRoute } from 'vue-router'
import { useMenuStore } from '@/stores/menu'
import { storeToRefs } from 'pinia'
import { SSEConnect } from '@/api/sse'
import { useRouter } from 'vue-router'
const router = useRouter()
const menuStore = useMenuStore()
const { menuSeen, isMobile } = storeToRefs(menuStore)
import { provide, readonly } from 'vue'
import type { RoomUserInfoType, RoomInfoType } from '@/types/room'
const emit = defineEmits(['update-name'])

const chatRooms = ref<ChatRoomType[]>([])
//라우터로 부터 방번호를 받아온다
const route = useRoute()
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

const updateRoomName = (name: string | undefined) => {
    roomUserInfo.value.roomName = name ? name : '방 제목'
    emit('update-name', roomUserInfo.value.roomName)
}

provide('roomUserInfo', readonly(roomUserInfo))
provide('roomUserNo', readonly(roomUserNo))

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

    eventSource.onmessage = (event) => {
        console.log('Server Sent Event 메시지를 받았습니다.', event.data)
    }

    // 서버로부터 알림 메시지가 오면 적절한 처리 로직을 수행
    eventSource.addEventListener('alarm', (event) => {
        console.log('새로운 알림이 도착했습니다', event.data)
    })

    eventSource.addEventListener('test', (event) => {
        console.log('test')
        const data = JSON.parse(event.data)
        console.log(data)
    })

    // 서버로부터 채팅 메시지가 왔다는 메시지를 받으면 적절한 처리 로직을 수행
    eventSource.addEventListener('chat', (event) => {
        console.log('새로운 메시지가 도착했습니다.', event.data)
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
            roomInfo.value = data.result
            roomUserInfo.value.profileUrl = data.result.userInfo.profileUrl
            roomUserInfo.value.roomNickname = data.result.userInfo.nickname
            roomUserInfo.value.roomUserNo = data.result.userInfo.roomUserNo
            roomUserInfo.value.roomName = data.result.roomName
            roomUserNo.value = data.result.userInfo.roomUserNo
            updateRoomName(data.result.roomName)
            SSEConnection(data.result.userInfo.roomUserNo)
        },
        (error) => {
            alert(error.response.data.message)
            router.replace('/')
        }
    )
}

onMounted(() => {
    getRoomData()
})

onUnmounted(() => {
    if (eventSource) {
        console.log('SSE 연결을 종료합니다.')
        eventSource.close()
    }
})
</script>

<template>
    <div class="flex flex-1 bg-A805RealWhite">
        <div v-for="room in chatRooms" :key="room.name">
            <ChatRoom :name="room.name" :imageUrl="room.imageUrl" @close-chat-room="removeChatRoom" />
        </div>
        <!-- pc버전이거나, 모바일 버전 + 메뉴가 체크된 상태일때만 nav가 보인다. -->
        <NavBar @make-room="makeRoom" :room-name="roomUserInfo.roomName" :room-info="roomInfo"
            v-if="!isMobile || menuSeen" />

        <!-- pc버전이거나, 모바일 버전 + 메뉴가 닫힌 상태일때만 이 영역 이 보인다. -->
        <RouterView v-if="!isMobile || !menuSeen" />
    </div>
</template>

<style scoped></style>
