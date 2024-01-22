<script setup lang="ts">
import LogoAtom from '../atoms/LogoAtom.vue'
import CharacterAtom from '../atoms/CharacterAtom.vue'
</script>

<template>
    <div class="flex w-[330px] h-[95px] items-center">
        <CharacterAtom customClass="w-[120px] " />
        <LogoAtom customClass="w-[220px]" />
    </div>
</template>

<style scoped></style>
