<script setup lang="ts">
import AvatarAtom from '@/components/atoms/AvatarAtom.vue'
import BadgeAtom from '@/components/atoms/BadgeAtom.vue'
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import TextAtom from '@/components/atoms/TextAtom.vue'
import { convertStringToRegistrationDateTime } from '@/utils/date'
import ReplyWriteForm from './ReplyWriteForm.vue'
import { ref, type Ref } from 'vue'
import type { Handler } from '@/types/common'
defineProps(['reply', 'nested', 'postWriterUserNo'])

const seenReplyWriteForm: Ref<boolean> = ref(false)
const modifyButtonHandler: Handler = () => {
    alert('댓글 수정 이벤트')
}
const deleteButtonHandler: Handler = () => {
    alert('댓글 삭제 이벤트')
}
const writeButtonHandler: Handler = () => {
    alert('댓글 작성 이벤트')
}
</script>

<template>
    <div class="flex flex-col border-b py-3" :class="nested ? 'ms-[50px]' : ''">
        <div class="flex">
            <AvatarAtom
                :image-url="reply.writerImageUrl"
                custom-class="profile w-[40px] h-[40px] me-[10px]"
            />
            <div class="flex flex-col w-full">
                <div class="flex items-center">
                    <TextAtom custom-class="font-bold"
                        >{{ reply.writer }} ({{ reply.writerEmail }})</TextAtom
                    >
                    <BadgeAtom
                        custom-class="bg-A805Red text-A805RealWhite h-[24px] text-[14px] ms-2"
                        v-if="reply.roomUserNo === postWriterUserNo"
                        >작성자</BadgeAtom
                    >
                </div>
                <TextAtom custom-class="text-[16px]">{{ reply.content }}</TextAtom>
                <div>
                    <TextAtom custom-class="text-[16px] text-A805DarkGrey">{{
                        convertStringToRegistrationDateTime('2022-03-10T13:22:09')
                    }}</TextAtom>
                    <ButtonAtom
                        custom-class="text-[16px] text-A805DarkGrey hover:text-A805Blue ms-5"
                        @button-click="() => (seenReplyWriteForm = true)"
                        >답글쓰기</ButtonAtom
                    >
                    <ButtonAtom
                        custom-class="text-[16px] text-A805DarkGrey hover:text-A805Blue ms-5"
                        @button-click="modifyButtonHandler"
                        >수정</ButtonAtom
                    >
                    <ButtonAtom
                        custom-class="text-[16px] text-A805DarkGrey hover:text-A805Blue ms-5"
                        @button-click="deleteButtonHandler"
                        >삭제</ButtonAtom
                    >
                </div>
            </div>
        </div>
        <ReplyWriteForm
            class="mt-5"
            nested="true"
            v-if="seenReplyWriteForm"
            @submit-button-handle="writeButtonHandler"
            @cancel-button-handle="() => (seenReplyWriteForm = false)"
        />
    </div>
</template>

<style scoped></style>
