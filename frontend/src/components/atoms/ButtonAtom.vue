<script setup lang="ts">
defineProps(['customClass'])
const emit = defineEmits(['buttonClick'])

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
