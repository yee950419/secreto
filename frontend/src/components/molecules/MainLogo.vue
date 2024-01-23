<script setup lang="ts">
import LogoAtom from '@/components/atoms/LogoAtom.vue'
import CharacterAtom from '@/components/atoms/CharacterAtom.vue'
</script>

<template>
    <div class="flex flex-col w-[300px] h-[360px]">
        <CharacterAtom customClass="w-full h-[234px]" />
        <LogoAtom customClass="w-full h-[126px]" />
    </div>
</template>

<style scoped></style>
