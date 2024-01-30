<script setup lang="ts">
import TextAtom from '@/components/atoms/TextAtom.vue'
import { convertStringToMobileRegistrationDate } from '@/utils/date'
defineProps(['board'])
</script>

<template>
    <div
        class="flex flex-1 flex-col cursor-pointer md:hidden text-left border-b mx-[20px] py-[20px] border-A805Grey"
    >
        <div class="flex flex-col"></div>
        <TextAtom custom-class="truncate text-[16px] text-A805Black">
            {{ board.title }}
        </TextAtom>
        <TextAtom custom-class=" text-[14px] text-A805Khaki">{{ board.writer }}</TextAtom>
        <div class="flex justify-start text-left text-[12px] text-A805Khaki">
            <span class="w-[90px]">{{
                convertStringToMobileRegistrationDate(board.registerAt)
            }}</span>
            <span class="w-[80px]">조회 : {{ board.hit }}</span>
            <span class="w-[80px]">추천 : {{ board.likedCount }}</span>
            <span class="w-[80px]">댓글 : {{ board.replyCount }}</span>
        </div>
    </div>
</template>

<style scoped></style>
