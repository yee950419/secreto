<script setup lang="ts">
import AvatarAtom from '@/components/atoms/AvatarAtom.vue'
import BadgeAtom from '@/components/atoms/BadgeAtom.vue'
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import TextAtom from '@/components/atoms/TextAtom.vue'
import { convertStringToRegistrationDateTime } from '@/utils/date'
import NestedReplyWriteForm from '@/components/molecules/board/NestedReplyWriteForm.vue'
import ReplyModifyForm from '@/components/molecules/board/ReplyModifyForm.vue'
import { inject, ref, type Ref } from 'vue'
import type { Handler } from '@/types/common'
import ModalTemplate from '@/components/template/ModalTemplate.vue'
import YesNoModalContent from '@/components/organisms/modal/YesNoModalContent.vue'
import { deleteReply } from '@/api/board'
import { useRoute } from 'vue-router'
const props = defineProps(['reply', 'nested', 'postWriterUserNo'])
const emit = defineEmits([
    'deleteSuccessHandle',
    'submitReplySuccessHandle',
    'modifyReplySuccessHandle'
])
const route = useRoute()

const roomNo: Ref<number> = ref(Number(route.params.roomNo))
const roomUserNo: Ref<number> = inject('roomUserNo', ref(-1))
const seenReplyWriteForm: Ref<boolean> = ref(false)
const seenReplyModifyForm: Ref<boolean> = ref(false)
const deleteButtonHandler: Handler = () => {
    deleteReply(
        roomNo.value,
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
        <div v-if="reply.deleteYn">삭제된 댓글입니다.</div>
        <div v-if="!reply.deleteYn" class="flex" v-show="!seenReplyModifyForm">
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
                        v-if="roomUserNo === reply.roomUserNo"
                        >수정</ButtonAtom
                    >
                    <ButtonAtom
                        custom-class="text-[16px] text-A805DarkGrey hover:text-A805Blue ms-5"
                        @button-click="deleteModalToggle"
                        v-if="roomUserNo === reply.roomUserNo"
                        >삭제</ButtonAtom
                    >
                </div>
            </div>
        </div>
        <ReplyModifyForm
            nested="true"
            v-if="seenReplyModifyForm"
            :reply="reply"
            @modify-reply-success-handle="
                () => {
                    seenReplyModifyForm = false
                    emit('modifyReplySuccessHandle')
                }
            "
            @cancel-button-handle="() => (seenReplyModifyForm = false)"
        />
        <NestedReplyWriteForm
            class="mt-5"
            v-if="seenReplyWriteForm"
            :parent-reply-no="reply.replyNo"
            :tag-user-no="reply.roomUserNo"
            @submit-reply-success-handle="
                () => {
                    emit('submitReplySuccessHandle')
                    seenReplyWriteForm = false
                }
            "
            @cancel-button-handle="() => (seenReplyWriteForm = false)"
        />
    </div>
</template>

<style scoped></style>
