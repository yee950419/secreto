<script setup lang="ts">
import TextAtom from '@/components/atoms/TextAtom.vue'

defineProps(['missions'])
</script>

<template>
    <div class="flex flex-col justify-center items-center w-full">
        <TextAtom custom-class="text-[24pt] font-Iceland text-A805Black">MISSIONS</TextAtom>
        <ul
            class="bg-white max-h-[500px] text-[1.25rem] md:text-[1.5rem] lg:text-[2rem] overflow-y-auto"
        >
            <li v-for="mission in missions" :key="mission" class="p-3">{{ mission.content }}</li>
        </ul>
    </div>
</template>

<style scoped></style>
