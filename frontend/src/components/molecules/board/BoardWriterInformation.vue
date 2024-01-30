<script setup lang="ts">
import AvatarAtom from '@/components/atoms/AvatarAtom.vue'
import TextAtom from '@/components/atoms/TextAtom.vue'
import { convertStringToRegistrationDateTime } from '@/utils/date'
defineProps([
    'writer',
    'writerEmail',
    'writerImageUrl',
    'registerAt',
    'hit',
    'likedCount',
    'replyCount'
])
</script>

<template>
    <div>
        <AvatarAtom :image-url="writerImageUrl" custom-class="profile w-[60px] h-[60px]" />
        <TextAtom
            ><span class="font-bold me-1">{{ writer }}</span>
            <span>({{ writerEmail }})</span></TextAtom
        >
        <span class="text-[14px]">{{ convertStringToRegistrationDateTime(registerAt) }}</span>
    </div>
</template>

<style scoped></style>
