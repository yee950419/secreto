<script setup lang="ts">
import type { Handler } from '@/types/common'
defineProps(['customClass', 'customId'])
const emit = defineEmits(['checkBoxChange'])
const checkBoxHandler: Handler = () => {
    emit('checkBoxChange')
}
</script>

<template>
    <div class="flex items-center" :class="customClass">
        <input @click="checkBoxHandler" type="checkbox" :id="customId" />
        <label :for="customId"><slot></slot></label>
    </div>
</template>

<style scoped></style>
