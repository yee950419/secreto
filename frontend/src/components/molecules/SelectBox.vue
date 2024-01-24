<script setup lang="ts">
defineProps(['customClass', 'customId'])
const emit = defineEmits([])
const options = [
    { value: 'customClass', label: 'Custom1' },
    { value: 'customId', label: 'button-style-1 button-border-violet button-claret' }
]
</script>

<template>
    <div
        class="flex relative rounded-md border border-A805DarkGrey overflow-hidden"
        :class="customClass"
    >
        <select class="px-1">
            <option v-for="option in options" :key="option.value" :value="option.value">
                {{ option.label }}
            </option>
        </select>

        <div
            class="bg-A805LightBlue text-white h-full w-[30px] text-center absolute right-0 pointer-events-none leading-[27px]"
        >
            ▼
        </div>
    </div>
</template>

<style scoped></style>
