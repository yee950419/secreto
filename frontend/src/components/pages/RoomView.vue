<script setup lang="ts">
import NavBar from '@/components/organisms/common/NavBar.vue'
import ChatRoom from '@/components/organisms/game/ChatRoom.vue'
import { reactive, onMounted, ref } from 'vue'
import type { ChatRoomType } from '@/types/chat'
import { getRoom, getRoomList } from '@/api/room'
// import { useRoute } from 'vue-router'
import { useMenuStore } from '@/stores/menu'
import { useUserStore } from '@/stores/user'
import { storeToRefs } from 'pinia'
const menuStore = useMenuStore()
const { menuSeen, isMobile } = storeToRefs(menuStore)
const emit = defineEmits(['update-name'])
const userStore = useUserStore()
const { userLogin } = userStore

const chatRooms = reactive<ChatRoomType[]>([])
//라우터로 부터 방번호를 받아온다
// const route = useRoute()
// const roomNo = ref(route.params.roomNo)

const roomNo = ref(1)
const roomName = ref<string | undefined>()

const updateRoomName = (name: string | undefined) => {
    roomName.value = name ? name : '방 제목'
    emit('update-name', roomName.value)
}

const getRoomData = () => {
    console.log('방정보 호출')
    getRoom(
        roomNo.value,
        ({ data }) => {
            console.log(data)
            updateRoomName(data.result.roomName)
        },
        (error) => {
            console.error('error', error)
        }
    )
}
// 방정보를 가져와서 방제목 매핑해주자
onMounted(() => {
    getRoomList(
        ({ data }) => {
            console.log(data)
        },
        (error) => {
            console.error('error', error)
        }
    )
    getRoomData()
})

const removeChatRoom = (name: string) => {
    const index = chatRooms.findIndex((room) => room.name === name)
    if (index !== -1) {
        chatRooms.splice(index, 1)
    }
}

const makeRoom = ({ name, imageUrl }: ChatRoomType) => {
    // 이미 만들어진 방인지 체크
    const existingRoom = chatRooms.find((room) => room.name === name)
    if (!existingRoom) {
        chatRooms.push({
            name: name,
            imageUrl: imageUrl
        })
    }
}
</script>

<template>
    <div class="flex flex-1 bg-A805White">
        <div v-for="room in chatRooms" :key="room.name">
            <ChatRoom
                :name="room.name"
                :imageUrl="room.imageUrl"
                @close-chat-room="removeChatRoom"
            />
        </div>
        <!-- pc버전이거나, 모바일 버전 + 메뉴가 체크된 상태일때만 nav가 보인다. -->
        <NavBar @make-room="makeRoom" :room-name="roomName" v-if="!isMobile || menuSeen" />

        <!-- pc버전이거나, 모바일 버전 + 메뉴가 닫힌 상태일때만 이 영역 이 보인다. -->
        <RouterView v-if="!isMobile || !menuSeen" />
    </div>
</template>

<style scoped></style>
