<script setup lang="ts">
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import ChatProfile from '@/components/molecules/ChatProfile.vue'
import { CloseOutlined } from '@ant-design/icons-vue'
import { ref, onMounted, onUnmounted } from 'vue'
const { imageUrl, name } = defineProps({
    imageUrl: {
        type: String,
        default: ''
    },
    name: {
        type: String,
        default: ''
    }
})

const emit = defineEmits(['close-chat-room'])

const closeChatRoom = () => {
    emit('close-chat-room', name)
}

const chatRoomRef = ref<HTMLElement | null>(null)
const dragging = ref(false)
const offsetX = ref(0)
const offsetY = ref(0)

const startDrag = (e: MouseEvent) => {
    if (!chatRoomRef.value || window.innerWidth <= 768) return

    dragging.value = true
    offsetX.value = e.clientX - chatRoomRef.value.offsetLeft
    offsetY.value = e.clientY - chatRoomRef.value.offsetTop
}

const drag = (e: MouseEvent) => {
    if (!dragging.value || !chatRoomRef.value) return
    chatRoomRef.value.style.left = e.clientX - offsetX.value + 'px'
    chatRoomRef.value.style.top = e.clientY - offsetY.value + 'px'
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

onMounted(() => {
    handleResize()
    window.addEventListener('resize', handleResize)
})

onUnmounted(() => {
    window.removeEventListener('resize', handleResize)
})
</script>

<template>
    <div
        class="modal absolute flex flex-col flex-1 overflow-hidden max-md:w-full max-md:min-h-[600px] md:min-h-[600px] md:w-[350px] md:rounded-md md:border-2 md:border-solid md:border-A805Black bg-A805Grey"
        ref="chatRoomRef"
        @mousedown="startDrag"
        @mousemove="drag"
        @mouseup="stopDrag"
    >
        <div
            class="flex flex-[1] px-[10px] items-center justify-between bg-A805White headerSection"
        >
            <ChatProfile :imageUrl="imageUrl" :name="name" /><CloseOutlined
                style="font-size: 24px"
                @click="closeChatRoom"
            />
        </div>
        <div class="flex flex-[6] flex-col bg-A805White contentsSection px-[10px]">
            <div class="flex items-end justify-start mb-2">
                <div class="bg-white p-3 rounded-md shadow-md">
                    <p>상대방이 쓴 메세지 내용</p>
                </div>
            </div>

            <!-- 내 댓글 -->
            <div class="flex items-end justify-end mb-2">
                <div class="bg-yellow-500 p-3 rounded-md shadow-md text-white">
                    <p>내가 쓴 메세지 내용</p>
                </div>
            </div>
        </div>
        <div class="flex flex-[1] inputSection">
            <textarea
                class="resize-none rounded-md border-2 border-solid border-A805Black cursor-text w-full bg-A805White m-[10px] p-[10px]"
                placeholder="내용을 입력해보세요"
            ></textarea>
        </div>
        <div class="flex justify-end">
            <ButtonAtom custom-class="chat-button rounded-md m-[10px]">전송</ButtonAtom>
        </div>
    </div>
</template>

<style scoped></style>
