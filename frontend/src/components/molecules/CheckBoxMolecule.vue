<script setup lang="ts">
import { defineProps, defineEmits } from 'vue'

const { customClass, customId, color } = defineProps(['customClass', 'customId', 'color'])
const emit = defineEmits()
</script>

<template>
    <div class="flex items-center" :class="customClass">
        <input :style="{ 'accent-color': color }" type="checkbox" :id="customId" />
        <label :for="customId"><slot></slot></label>
    </div>
</template>

<style scoped></style>
