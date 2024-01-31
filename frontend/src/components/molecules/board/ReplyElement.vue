<script setup lang="ts">
import AvatarAtom from '@/components/atoms/AvatarAtom.vue'
import BadgeAtom from '@/components/atoms/BadgeAtom.vue'
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import TextAtom from '@/components/atoms/TextAtom.vue'
import { convertStringToRegistrationDateTime } from '@/utils/date'
defineProps(['reply', 'nested'])
</script>

<template>
    <div class="flex border-b py-3" :class="nested ? 'ms-[50px]' : ''">
        <AvatarAtom
            :image-url="reply.writerImageUrl"
            custom-class="profile w-[40px] h-[40px] me-[10px]"
        />
        <div class="flex flex-col">
            <div class="flex items-center">
                <TextAtom custom-class="font-bold"
                    >{{ reply.writer }} ({{ reply.writerEmail }})</TextAtom
                >
                <BadgeAtom custom-class="bg-A805Red text-A805RealWhite h-[24px] text-[14px] ms-2"
                    >작성자</BadgeAtom
                >
            </div>
            <TextAtom custom-class="text-[16px]">콘텐츠</TextAtom>
            <div>
                <TextAtom custom-class="text-[16px] text-A805DarkGrey">{{
                    convertStringToRegistrationDateTime('2022-03-10T13:22:09')
                }}</TextAtom>
                <ButtonAtom custom-class="text-[16px] text-A805DarkGrey hover:text-A805Blue ms-5"
                    >답글쓰기</ButtonAtom
                >
                <ButtonAtom custom-class="text-[16px] text-A805DarkGrey hover:text-A805Blue ms-5"
                    >수정</ButtonAtom
                >
                <ButtonAtom custom-class="text-[16px] text-A805DarkGrey hover:text-A805Blue ms-5"
                    >삭제</ButtonAtom
                >
            </div>
        </div>
    </div>
</template>

<style scoped></style>
