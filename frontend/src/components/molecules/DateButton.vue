<script setup lang="ts">
import ButtonAtom from '@/components/atoms/ButtonAtom.vue'
import { ref, type Ref } from 'vue'
import type { Handler } from '@/types/common'

const props = defineProps([
    'label',
    'customClass',
    'labelClass',
    'inputClass',
    'buttonClass',
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
const inputRef: Ref<string> = ref(props.defaultValue ? props.defaultValue : '')
const model = defineModel()
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
        <div class="flex items-center">
            <ButtonAtom
                class="rounded-e-none button-style-7 button-blue text-white"
                :class="buttonClass"
                @buttonClick="buttonClick"
                >-</ButtonAtom
            >
            <div class="flex justify-center content-center items-center">
                <div class="w-[50%]">
                    <input
                        class="text-right w-[100%]"
                        :id="customId"
                        type="number"
                        :class="inputClass"
                        :readonly="readonly"
                        :name="name"
                        :min="min"
                        :max="max"
                        :hidden="hidden"
                        @change="handleChange"
                        v-model.lazy="model"
                    />
                </div>
                <div class="w-[50%]">
                    <p class="text-left">일 마다</p>
                </div>
            </div>
            <ButtonAtom
                class="rounded-s-none button-style-7 button-blue text-white"
                :class="buttonClass"
                @buttonClick="buttonClick"
                >+</ButtonAtom
            >
        </div>
    </div>
</template>

<style scoped></style>
