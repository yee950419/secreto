<script setup lang="ts">
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import CharacterAtom from '@/components/atoms/CharacterAtom.vue'
import TextAtom from '@/components/atoms/TextAtom.vue'
import type { Handler } from '@/types/common'

defineProps(['title', 'titleClass', 'contentMessages', 'contentClass', 'buttonLabel'])
const emit = defineEmits(['buttonClick'])
const handleClick: Handler = () => {
    emit('buttonClick')
}
</script>

<template>
    <div
        class="bg-A805RealWhite flex flex-col justify-between items-center h-[500px] w-full max-w-[800px] py-[20px] px-[40px] scroll-container"
    >
        <TextAtom custom-class="text-[48px] font-Iceland" :class="titleClass">{{ title }}</TextAtom>
        <CharacterAtom class="w-[160px]"></CharacterAtom>
        <div class="h-[80px] text-[24px] leading-[24px] flex flex-col justify-between items-center">
            <TextAtom v-for="(message, i) in contentMessages" :key="i + message">
                {{ message }}
            </TextAtom>
        </div>
        <ButtonAtom
            custom-class="button-style-2 w-[300px] button-shadow button-cream my-[30px]"
            @button-click="handleClick"
            >{{ buttonLabel }}</ButtonAtom
        >
    </div>
</template>

<style scoped></style>
