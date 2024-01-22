<script setup lang="ts">
import { defineProps, defineEmits } from 'vue'

const { label, customClass } = defineProps(['label', 'customClass'])
const emit = defineEmits()

type ClickHandler = () => void

const handleClick: ClickHandler = () => {
    emit('click')
}
</script>

<template>
    <button :class="customClass" @click="handleClick">
        <slot></slot>
    </button>
</template>

<style scoped></style>
