<script setup lang="ts">
import { defineProps, defineEmits } from 'vue'
import TextAtom from '@/components/atoms/TextAtom.vue'
const props = defineProps({
    customClass: {
        type: String,
        default: ''
    },
    active: {
        type: Boolean,
        default: false
    }
})

const emit = defineEmits(['menuClick'])

const handleItemClick = () => {
    emit('menuClick', props.active)
}
</script>

<template>
    <div :class="[customClass, { active: active }]" @click="handleItemClick">
        <TextAtom :customClass="`${customClass === 'menu-item' ? 'ml-[40px]' : 'ml-[80px]'}`">
            <slot></slot
        ></TextAtom>
    </div>
</template>

<style scoped></style>
