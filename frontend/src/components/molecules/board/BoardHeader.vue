<script setup lang="ts">
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import type { Handler } from '@/types/common'
import { ref, type Ref } from 'vue'

const emit = defineEmits(['noticeButtonHandle', 'certificateButtonHandle', 'BoastButtonHandle'])

const BoardCategory = Object.freeze({
    BOAST: 'boast',
    CERTIFICATE: 'certificate',
    NOTICE: 'notice'
})
const category: Ref<string> = ref(BoardCategory.NOTICE)

const clickNoticeBoardButtonHandler: Handler = () => {
    if (category.value !== BoardCategory.NOTICE) {
        category.value = BoardCategory.NOTICE
        emit('noticeButtonHandle')
    }
}
const clickCertificationBoardButtonHandler: Handler = () => {
    if (category.value !== BoardCategory.CERTIFICATE) {
        category.value = BoardCategory.CERTIFICATE
        emit('certificateButtonHandle')
    }
}
const clickBoastBoardButtonHandler: Handler = () => {
    if (category.value !== BoardCategory.BOAST) {
        category.value = BoardCategory.BOAST
        emit('BoastButtonHandle')
    }
}
</script>

<template>
    <div class="flex gap-[50px] text-A805DarkGrey">
        <ButtonAtom
            :class="category === BoardCategory.NOTICE ? 'text-A805Black' : ''"
            custom-class="text-[28px]"
            @click="clickNoticeBoardButtonHandler"
        >
            공지 게시판
        </ButtonAtom>
        <ButtonAtom
            :class="category === BoardCategory.CERTIFICATE ? 'text-A805Black' : ''"
            custom-class="text-[28px]"
            @click="clickCertificationBoardButtonHandler"
        >
            인증 게시판
        </ButtonAtom>
        <ButtonAtom
            :class="category === BoardCategory.BOAST ? 'text-A805Black' : ''"
            custom-class="text-[28px]"
            @click="clickBoastBoardButtonHandler"
        >
            자랑 게시판
        </ButtonAtom>
    </div>
</template>

<style scoped></style>
