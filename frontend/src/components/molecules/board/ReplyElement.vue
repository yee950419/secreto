<script setup lang="ts">
import AvatarAtom from '@/components/atoms/AvatarAtom.vue'
import BadgeAtom from '@/components/atoms/BadgeAtom.vue'
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import TextAtom from '@/components/atoms/TextAtom.vue'
import { convertStringToRegistrationDateTime } from '@/utils/date'
import ReplyWriteForm from './ReplyWriteForm.vue'
import ReplyModifyForm from './ReplyModifyForm.vue'
import { ref, type Ref } from 'vue'
import type { Handler } from '@/types/common'
import ModalTemplate from '@/components/template/ModalTemplate.vue'
import YesNoModalContent from '@/components/organisms/modal/YesNoModalContent.vue'
import { deleteReply } from '@/api/board'
const props = defineProps(['reply', 'nested', 'postWriterUserNo'])
const emit = defineEmits(['deleteSuccessHandle'])

const seenReplyWriteForm: Ref<boolean> = ref(false)
const seenReplyModifyForm: Ref<boolean> = ref(false)
const deleteButtonHandler: Handler = () => {
    deleteReply(
        props.reply.replyNo,
        (response) => {
            console.log(response.data.message)
            deleteModalToggle()
            emit('deleteSuccessHandle')
        },
        (error) => alert(error.message)
    )
}
const deleteModalSeen: Ref<boolean> = ref(false)
const deleteModalToggle = () => (deleteModalSeen.value = !deleteModalSeen.value)
</script>

<template>
    <!-- Delete Model -->
    <ModalTemplate
        custom-id="modal"
        custom-class="modal-template-style-1 w-[350px]"
        :seen="deleteModalSeen"
        v-if="deleteModalSeen"
        @modal-close="deleteModalToggle"
    >
        <YesNoModalContent
            @yes-button-handle="deleteButtonHandler"
            @no-button-handle="deleteModalToggle"
            content-message="댓글을 삭제하시겠습니까?"
        />
    </ModalTemplate>
    <div class="flex flex-col border-b py-3" :class="nested ? 'ms-[50px]' : ''">
        <div class="flex" v-show="!seenReplyModifyForm">
            <AvatarAtom
                :image-url="reply.writerProfileUrl"
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
                        convertStringToRegistrationDateTime(reply.registerAt)
                    }}</TextAtom>
                    <ButtonAtom
                        custom-class="text-[16px] text-A805DarkGrey hover:text-A805Blue ms-5"
                        @button-click="
                            () => {
                                seenReplyWriteForm = true
                                seenReplyModifyForm = false
                            }
                        "
                        >답글쓰기</ButtonAtom
                    >
                    <ButtonAtom
                        custom-class="text-[16px] text-A805DarkGrey hover:text-A805Blue ms-5"
                        @button-click="
                            () => {
                                seenReplyWriteForm = false
                                seenReplyModifyForm = true
                            }
                        "
                        >수정</ButtonAtom
                    >
                    <ButtonAtom
                        custom-class="text-[16px] text-A805DarkGrey hover:text-A805Blue ms-5"
                        @button-click="deleteModalToggle"
                        >삭제</ButtonAtom
                    >
                </div>
            </div>
        </div>
        <ReplyModifyForm
            nested="true"
            v-show="seenReplyModifyForm"
            :default-value="reply.content"
            @cancel-button-handle="() => (seenReplyModifyForm = false)"
        />
        <ReplyWriteForm
            class="mt-5"
            nested="true"
            v-if="seenReplyWriteForm"
            @cancel-button-handle="() => (seenReplyWriteForm = false)"
        />
    </div>
</template>

<style scoped></style>
