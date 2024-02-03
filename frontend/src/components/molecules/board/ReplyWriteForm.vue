<script setup lang="ts">
import { postReply } from '@/api/board'
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import TextAtom from '@/components/atoms/TextAtom.vue'
import { useUserStore } from '@/stores/user'
import { ref } from 'vue'
import type { ReplyWriteRequestType } from '@/types/board'
const props = defineProps(['nested', 'postNo'])

const userStore = useUserStore()
const emit = defineEmits(['submitButtonHandle', 'cancelButtonHandle'])

const textArea = ref<HTMLInputElement | null>(null)
const resize = () => {
    if (textArea.value) {
        textArea.value.style.height = 'auto'
        textArea.value.style.height = textArea.value.scrollHeight + 'px'
    }
}

const writeReplyHandler = () => {
    // postReply(props.postNo, )
    const replyRequest: ReplyWriteRequestType = {
        postNo: 0,
        roomUserNo: 1,
        content: ''
    }
    if (textArea.value) {
        replyRequest.postNo = props.postNo
        replyRequest.content = textArea.value.value
    }
    console.log(replyRequest)
    postReply(
        props.postNo,
        replyRequest,
        (response) => {
            console.log(response)
        },
        (error) => alert(error.message)
    )
    emit('submitButtonHandle')
}
</script>

<template>
    <div class="flex flex-col flex-1 border-2 rounded-lg p-3 text-[16px]">
        <TextAtom custom-class="font-bold">
            {{ userStore.userInfo.nickname }} ({{ userStore.userInfo.email }})</TextAtom
        >
        <textarea
            ref="textArea"
            class="min-h-[40px] resize-none focus:outline-none my-2"
            placeholder="댓글을 남겨보세요"
            @input="resize"
        ></textarea>
        <ButtonAtom
            custom-class="button-claret rounded-md font-bold"
            @button-click="writeReplyHandler"
        >
            등록
        </ButtonAtom>
        <ButtonAtom
            v-if="nested"
            custom-class="button-white rounded-md font-bold mt-2"
            @button-click="() => emit('cancelButtonHandle')"
        >
            취소
        </ButtonAtom>
    </div>
</template>

<style scoped></style>
