<script setup lang="ts">
import { defineProps, defineEmits } from 'vue'

const { customClass } = defineProps(['customClass'])
const emit = defineEmits()
</script>

<template>
    <input type="text" :class="customClass" value="input-atom-default-value">
</template>

<style scoped></style>
