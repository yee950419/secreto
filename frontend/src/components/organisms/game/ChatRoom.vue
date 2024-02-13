<script setup lang="ts">
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import ChatProfile from '@/components/molecules/game/ChatProfile.vue'
import { CloseOutlined } from '@ant-design/icons-vue'
import type { Message } from '@/types/chat'
import type { Socket } from 'socket.io-client'
import { ref, onMounted, onUnmounted, watch, type Ref, nextTick } from 'vue'
import { io } from 'socket.io-client'

const emit = defineEmits(['close-chat-room'])

const { imageUrl, name } = defineProps({
    imageUrl: {
        type: String,
        default: ''
    },
    name: {
        type: String,
        default: ''
    },
    customClass: {
        type: String as () => string,
        default: ''
    }
})

const messages = ref<Message[]>([])
const attemp = ref(0)
const textMessage = ref<string>('')
const chatRoomRef = ref<HTMLElement | null>(null)
const dragging = ref(false)
const offsetX = ref(0)
const offsetY = ref(0)

const socket: Socket = io('http://localhost:3000', {
    reconnectionDelay: 1000, // 1초마다 재시도
    reconnectionDelayMax: 5000, // 최대 5초까지 재시도 간격 증가
    reconnectionAttempts: 3 // 최대 5번 재시도
})

socket.on('connect', () => {
    displayConnect('You are connected!', 'connect')
})

socket.on('connect_error', () => {
    if (attemp.value === 3) {
        displayConnect('서버 에러, 다음에 다시 시도해주세요', 'connect')
        return
    }
    displayConnect(`연결실패, 재시도중... ${++attemp.value}`, 'connect')
})

socket.emit('join-room', name, (datas: Message[]) => {
    messages.value = datas
})

socket.on('message', ({ content, type }: Message) => {
    console.log(content, type)
    displayMessage(content, type)
})

const displayConnect = (message: string, type: string) => {
    messages.value.push({ content: message, type: type })
}

const displayMessage = (message: string, type: string) => {
    messages.value.push({ content: message, type: type })
}

const sendMessage = () => {
    // 첫번째 인자로 이벤트명, 두번째 인자로 데이터, 세번째 인자로 방식별자
    socket.emit('message', { content: textMessage.value, type: 'sent' }, name)
    displayMessage(textMessage.value, 'sent')
}


const closeChatRoom = () => {
    socket.close()
    emit('close-chat-room', name)
}

const startDrag = (e: MouseEvent) => {
    if (!chatRoomRef.value || window.innerWidth <= 768) return

    dragging.value = true
    offsetX.value = e.clientX - chatRoomRef.value.offsetLeft
    offsetY.value = e.clientY - chatRoomRef.value.offsetTop
}

const drag = (e: MouseEvent) => {
    if (!dragging.value || !chatRoomRef.value) return

    const maxX = window.innerWidth - chatRoomRef.value.clientWidth - 10
    const maxY = window.innerHeight - chatRoomRef.value.clientHeight - 10

    let newLeft = e.clientX - offsetX.value
    let newTop = e.clientY - offsetY.value

    // Ensure the modal stays within the boundaries
    newLeft = Math.max(0, Math.min(newLeft, maxX))
    newTop = Math.max(0, Math.min(newTop, maxY))

    chatRoomRef.value.style.left = newLeft + 'px'
    chatRoomRef.value.style.top = newTop + 'px'
}

const stopDrag = () => {
    dragging.value = false
}

const handleResize = () => {
    if (window.innerWidth <= 768) {
        chatRoomRef.value?.style.removeProperty('left')
        chatRoomRef.value?.style.removeProperty('top')
    }
}


const messageContainer = ref<HTMLDivElement | null>(null);

const scrollToBottom = () => {
    const container = messageContainer.value;
    if (container) {
        container.scrollTop = container.scrollHeight;
    }
};

// 메시지 배열이 변경될 때마다 스크롤을 맨 아래로 이동시킵니다.
watch(() => messages, () => {
    nextTick(() => {
        scrollToBottom();
    });
}, { deep: true });


onMounted(() => {
    handleResize()
    window.addEventListener('resize', handleResize)
})

onUnmounted(() => {
    window.removeEventListener('resize', handleResize)
})
</script>

<template>
    <div class="md:absolute z-10 max-md:relative max-md:min-h-[650px]  max-md:max-h-[650px] flex flex-col  overflow-hidden max-md:w-screen max-md:h-full  md:h-[600px] md:w-[350px] md:rounded-md md:border-2 md:border-solid md:border-A805Black bg-A805Grey md:left-[50%] md:top-[20%] cursor-pointer"
        ref="chatRoomRef" @mousedown="startDrag" @mousemove="drag" @mouseup="stopDrag">
        <div class="flex h-[15%]  px-[10px] items-center justify-between headerSection">
            <ChatProfile :imageUrl="imageUrl" :name="name" />
            <CloseOutlined style="font-size: 24px" @click="closeChatRoom" />
        </div>
        <div class="flex h-[65%] flex-col bg-A805White contentsSection px-[10px]  overflow-y-scroll" ref="messageContainer">
            <div v-for="(message, index) in messages" :key="index">
                <div class="flex items-end justify-start mb-2" v-if="message.type === 'received'">
                    <div class="bg-white p-3 rounded-md shadow-md">
                        <p>{{ message.content }}</p>
                    </div>
                </div>

                <div class="flex items-end justify-end mb-2" v-else-if="message.type === 'sent'">
                    <div class="bg-yellow-500 p-3 rounded-md shadow-md text-white">
                        <p>{{ message.content }}</p>
                    </div>
                </div>

                <div class="text-center" v-else>
                    <p>{{ message.content }}</p>
                </div>
            </div>
        </div>
        <div class="flex flex-[1] inputSection">
            <textarea
                class="resize-none rounded-md border-2 border-solid border-A805Black cursor-text w-full bg-A805White m-[10px] p-[10px]"
                v-model="textMessage" placeholder="내용을 입력해보세요"></textarea>
        </div>
        <div class="flex  justify-end">
            <ButtonAtom custom-class="chat-button rounded-md m-[10px]" @button-click="sendMessage">전송</ButtonAtom>
        </div>
    </div>
</template>

<style scoped></style>
