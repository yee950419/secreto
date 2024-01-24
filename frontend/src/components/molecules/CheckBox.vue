<script setup lang="ts">
defineProps(['customClass', 'customId'])
const emit = defineEmits([])
</script>

<template>
    <div class="flex items-center" :class="customClass">
        <input type="checkbox" :id="customId" />
        <label :for="customId"><slot></slot></label>
    </div>
</template>

<style scoped></style>
