<script setup lang="ts">
import type { Handler } from '@/types/common'

defineProps(['customClass'])
const emit = defineEmits(['buttonClick'])

const handleClick: Handler = () => {
    emit('buttonClick')
}
</script>

<template>
    <button :class="customClass" @click="handleClick">
        <slot></slot>
    </button>
</template>

<style scoped></style>
