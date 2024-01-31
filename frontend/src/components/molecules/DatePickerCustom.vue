<script setup lang="ts">
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import { watch, ref, type Ref } from 'vue'
import type { Handler } from '@/types/common'

const props = defineProps(['customClass'])
const emit = defineEmits([])

const model = defineModel()
</script>
<template>
    <div class="flex flex-col" :class="customClass">
        <label for="test">Start Date:</label>
        <input
            type="datetime-local"
            name="startDateTime"
            id="startDateTime"
            value="2024-01-30"
            min="2024-01-29"
            max="2025-01-30"
            class="w-[180px]"
            :class="customClass"
        />
        <input
            type="datetime-local"
            name="EndDateTime"
            id="EndDateTime"
            value="2024-01-30"
            min="2024-01-29"
            max="2025-01-30"
            class="w-[180px]"
            :class="customClass"
        />
    </div>
</template>

<style scoped></style>
