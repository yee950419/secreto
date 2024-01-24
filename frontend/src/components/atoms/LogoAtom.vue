<script setup lang="ts">
defineProps(['customClass'])
const emit = defineEmits(['click'])

type ClickHandler = () => void

const handleClick: ClickHandler = () => {
    emit('click')
}
</script>

<template>
    <div :class="customClass" @click="handleClick">
        <img src="@/assets/images/logo.png" alt="Logo" />
    </div>
</template>

<style scoped></style>
