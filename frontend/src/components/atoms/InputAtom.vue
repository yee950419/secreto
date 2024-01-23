<script setup lang="ts">
import { defineProps, defineEmits } from 'vue'

const { customClass, customValue, customId } = defineProps(['customClass','customValue', 'customId'])
const emit = defineEmits()
</script>

<template>
    <input type="text" :class="customClass" :value="customValue" :id="customId" >
</template>

<style scoped></style>
