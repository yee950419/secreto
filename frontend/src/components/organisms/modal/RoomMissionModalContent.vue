<script setup lang="ts">
import TextAtom from '@/components/atoms/TextAtom.vue'

defineProps(['missions'])
</script>

<template>
    <div class="flex flex-col justify-center items-center w-full">
        <TextAtom custom-class="text-[48px] font-Iceland text-A805Black">MISSIONS</TextAtom>
        <ul class="bg-white w-full max-h-[500px] text-[15pt] overflow-y-auto">
            <li v-for="mission in missions" :key="mission" class="p-3">{{ mission.content }}</li>
        </ul>
    </div>
</template>

<style scoped></style>
