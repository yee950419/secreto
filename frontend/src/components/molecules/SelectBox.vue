<script setup lang="ts">
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'

defineProps(['options', 'customClass', 'customId', 'buttonClass'])
const emit = defineEmits([])
</script>

<template>
    <div
        class="flex relative rounded-md border border-A805DarkGrey overflow-hidden"
        :class="customClass"
    >
        <select class="px-1 w-full">
            <option v-for="option in options" :key="option.value" :value="option.value">
                {{ option.label }}
            </option>
        </select>

        <ButtonAtom
            class="bg-A805LightBlue text-A805White h-full w-[30px] absolute right-0 pointer-events-none flex justify-center items-center"
            :class="buttonClass"
        >
            ▼
        </ButtonAtom>
    </div>
</template>

<style scoped></style>
