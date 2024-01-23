<script setup lang="ts">
import LogoAtom from '@/components/atoms/LogoAtom.vue'
import CharacterAtom from '@/components/atoms/CharacterAtom.vue'
const { size } = defineProps(['size'])
</script>

<template>
    <div
        class="flex flex-col justify-center content-center"
        :style="{ width: `${size}px`, height: `${size}px`, padding: `${size / 10}px` }"
    >
        <CharacterAtom customClass="w-full" />
        <LogoAtom customClass="w-full" />
    </div>
</template>

<style scoped></style>
