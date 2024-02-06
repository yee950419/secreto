<script setup lang="ts">
import ChatRoom from '@/components/organisms/game/ChatRoom.vue'
import { ref } from 'vue'
//const emit = defineEmits(['make-room'])

interface ChatRoomType {
    name: string
    imageUrl: string
}

const chatRooms = ref<ChatRoomType[]>([])

const removeChatRoom = (name: string) => {
    console.log(name, '찾아서 삭제하겠습니다!')
    const index = chatRooms.value.findIndex((room) => room.name === name)
    if (index !== -1) {
        chatRooms.value.splice(index, 1)
    }
}

const makeRoom = (roomName: string) => {
    // 이미 만들어진 방인지 체크
    const existingRoom = chatRooms.value.find((room) => room.name === roomName)
    if (!existingRoom) {
        console.log(roomName, '방을 만들겠습니다!')
        chatRooms.value.push({
            name: roomName,
            imageUrl: 'src/assets/images/character.png'
        })
    }

    console.log(chatRooms)
    console.log('부모 컴포넌트에 방 만들기 요청!')
    // emit('make-room', {
    //     name: '마니또',
    //     imageUrl: 'src/assets/images/character.png'
    // })
}
</script>

<template>
    <div class="flex flex-1">
        <div class="flex flex-1 flex-col w-full h-full">
            <button @click="makeRoom('마니또')">마니또와의 채팅</button>
            <button @click="makeRoom('마니띠')">마니띠와의 채팅</button>
            <button @click="makeRoom('단체채팅')">단체채팅</button>

            <div v-for="room in chatRooms" :key="room.name">
                <ChatRoom
                    :name="room.name"
                    :imageUrl="room.imageUrl"
                    @close-chat-room="removeChatRoom"
                />
            </div>
        </div>
    </div>
</template>

<style scoped></style>
