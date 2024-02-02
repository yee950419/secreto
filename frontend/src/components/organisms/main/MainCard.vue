<script setup lang="ts">
import MainLogo from '@/components/molecules/common/MainLogo.vue'
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import type { Handler } from '@/types/common'

defineProps(['buttonLabelRef'])
const emit = defineEmits(['buttonClick'])
const buttonClickHandler: Handler = () => {
    emit('buttonClick')
}
</script>

<template>
    <div
        class="bg-A805Cream flex flex-col justify-between items-center h-full aspect-[4/5] p-[20px]"
    >
        <MainLogo />
        <slot></slot>
        <ButtonAtom
            custom-class="button-style-1 button-claret button-shadow m-[20px]"
            @button-click="buttonClickHandler"
            >{{ buttonLabelRef }}</ButtonAtom
        >
    </div>
</template>

<style scoped></style>
