<script setup lang="ts">
import { defineProps, defineEmits, ref } from 'vue'
const {
    label,
    customClass,
    labelClass,
    inputClass,
    customId,
    defaultValue,
    placeHolder,
    type,
    readonly,
    hidden,
    min,
    max,
    name
} = defineProps([
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
const emit = defineEmits()

const inputRef = ref(defaultValue)
type ChangeHandler = () => void
const handleChange: ChangeHandler = () => {
    emit('input-change', inputRef.value)
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
            @change="handleChange"
            v-model.lazy="inputRef"
        />
    </div>
</template>

<style scoped></style>
