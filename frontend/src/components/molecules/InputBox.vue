<script setup lang="ts">
import { ref, type Ref } from 'vue'
import type { Handler } from '@/types/common'

const props = defineProps([
    'label',
    'customClass',
    'labelClass',
    'inputClass',
    'customId',
    'defaultValue',
    'placeHolder',
    'type',
    'readonly',
    'hidden',
    'min',
    'max',
    'name'
])
const emit = defineEmits(['inputEnter'])
const model = defineModel()
const handleEnter: Handler = () => {
    emit('inputEnter')
}
</script>

<template>
    <div class="flex flex-col" :class="customClass">
        <label :class="labelClass" :for="customId" v-if="label && !hidden">{{ label }}</label>
        <input
            :id="customId"
            :type="type"
            :class="inputClass"
            :placeholder="placeHolder"
            :readonly="readonly"
            :name="name"
            :min="min"
            :max="max"
            :hidden="hidden"
            v-model="model"
            @keypress.enter="handleEnter"
        />
    </div>
</template>

<style scoped></style>
