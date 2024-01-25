<script setup lang="ts">
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import { ref } from 'vue'
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
    'name',
    'buttonLabel'
])
const emit = defineEmits(['inputChange', 'buttonClick'])
const inputRef = ref(props.defaultValue ? props.defaultValue : '')

const handleChange: Handler = () => {
    emit('inputChange', inputRef.value)
}
const buttonClick: Handler = () => {
    emit('buttonClick', inputRef.value)
}
</script>

<template>
    <div class="flex flex-col" :class="customClass">
        <label :class="labelClass" :for="customId" v-if="label && !hidden">{{ label }}</label>
        <div class="flex">
            <input
                class="rounded-e-none"
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
            <ButtonAtom
                class="rounded-s-none button-style-6 button-claret pe-[3px]"
                @buttonClick="buttonClick"
                >{{ buttonLabel }}</ButtonAtom
            >
        </div>
    </div>
</template>

<style scoped></style>
