<script setup lang="ts">
const { customClass } = defineProps(['customClass'])
</script>

<template>
    <text :class="customClass">
        <slot></slot>
    </text>
</template>

<style scoped></style>
