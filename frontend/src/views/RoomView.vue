<script setup lang="ts">
import NavBar from '@/components/organisms/NavBar.vue'
import ChatRoom from '@/components/organisms/ChatRoom.vue'
import { reactive } from 'vue'
import type { ChatRoomType } from '@/types/chat'
const chatRooms = reactive<ChatRoomType[]>([])

const removeChatRoom = (name: string) => {
    console.log(name, '찾아서 삭제하겠습니다!')
    const index = chatRooms.findIndex((room) => room.name === name)
    if (index !== -1) {
        chatRooms.splice(index, 1)
    }
}

const makeRoom = ({ name, imageUrl }: ChatRoomType) => {
    // 이미 만들어진 방인지 체크
    const existingRoom = chatRooms.find((room) => room.name === name)
    if (!existingRoom) {
        console.log(name, '방을 만들겠습니다!')
        chatRooms.push({
            name: name,
            imageUrl: imageUrl
        })
    }
}
</script>

<template>
    <div class="flex flex-1 bg-red-300">
        <div v-for="room in chatRooms" :key="room.name">
            <ChatRoom
                :name="room.name"
                :imageUrl="room.imageUrl"
                @close-chat-room="removeChatRoom"
            />
        </div>
        <NavBar @make-room="makeRoom" />
        <RouterView />
    </div>
</template>

<style scoped></style>
