<script setup lang="ts">
const { imageUrl, nickName, customClass } = defineProps(['customClass', 'imageUrl', 'nickName'])
import TextAtom from '@/components/atoms/TextAtom.vue'
import AvatarAtom from '@/components/atoms/AvatarAtom.vue'
</script>

<template>
    <div class="flex flex-col text-center w-[200px] h-[200px]" :class="customClass">
        <AvatarAtom :imageUrl="imageUrl" />
        <TextAtom>{{ nickName }}</TextAtom>
    </div>
</template>

<style scoped></style>
