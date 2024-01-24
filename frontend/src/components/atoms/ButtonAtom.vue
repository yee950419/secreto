<script setup lang="ts">
import { defineProps, defineEmits } from 'vue'

const { customClass } = defineProps(['customClass'])
const emit = defineEmits()

type ClickHandler = () => void

const handleClick: ClickHandler = () => {
    emit('buttonClick')
}
</script>

<template>
    <button :class="customClass" @click="handleClick">
        <slot></slot>
    </button>
</template>

<style scoped></style>
