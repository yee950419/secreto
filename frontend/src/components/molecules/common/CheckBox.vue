<script setup lang="ts">
const props = defineProps(['customClass', 'customId', 'inputClass'])
const model = defineModel({ default: false })
</script>

<template>
    <div class="flex items-center" :class="customClass">
        <input
            class="transform scale-150 check-box-style"
            :class="inputClass"
            type="checkbox"
            :id="customId"
            v-model="model"
        />
        <label :for="customId"><slot></slot></label>
    </div>
</template>

<style scoped></style>
