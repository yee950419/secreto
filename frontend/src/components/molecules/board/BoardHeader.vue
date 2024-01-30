<script setup lang="ts">
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import type { Handler } from '@/types/common'
import { ref, type Ref } from 'vue'

const BoardCategory = Object.freeze({
    BOAST: 'boast',
    CERTIFICATION: 'certification',
    NOTICE: 'notice'
})
const category: Ref<string> = ref(BoardCategory.NOTICE)

const clickNoticeBoardButtonHandler: Handler = () => {
    if (category.value !== BoardCategory.NOTICE) {
        category.value = BoardCategory.NOTICE
    }
}
const clickCertificationBoardButtonHandler: Handler = () => {
    if (category.value !== BoardCategory.CERTIFICATION) {
        category.value = BoardCategory.CERTIFICATION
    }
}
const clickBoastBoardButtonHandler: Handler = () => {
    if (category.value !== BoardCategory.BOAST) {
        category.value = BoardCategory.BOAST
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
            :class="category === BoardCategory.CERTIFICATION ? 'text-A805Black' : ''"
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
