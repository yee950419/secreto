<script setup lang="ts">
import AvatarAtom from '@/components/atoms/AvatarAtom.vue'
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import TextAtom from '@/components/atoms/TextAtom.vue'
import { convertStringToRegistrationDateTime } from '@/utils/date'
</script>

<template>
    <div class="flex border-b py-3">
        <AvatarAtom :image-url="null" custom-class="profile w-[40px] h-[40px] me-[10px]" />
        <div class="flex flex-col">
            <TextAtom custom-class="font-bold">닉네임 (이메일)</TextAtom>
            <TextAtom custom-class="text-[16px]">콘텐츠</TextAtom>
            <div>
                <TextAtom custom-class="text-[16px] text-A805DarkGrey">{{
                    convertStringToRegistrationDateTime('2022-03-10T13:22:09')
                }}</TextAtom>
                <ButtonAtom custom-class="text-[16px] text-A805DarkGrey hover:text-A805Blue ms-5"
                    >답글쓰기</ButtonAtom
                >
                <ButtonAtom custom-class="text-[16px] text-A805DarkGrey hover:text-A805Blue ms-5"
                    >삭제</ButtonAtom
                >
            </div>
        </div>
    </div>
</template>

<style scoped></style>
