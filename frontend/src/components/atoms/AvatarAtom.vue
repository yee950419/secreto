<script setup lang="ts">
import { defineProps, defineEmits } from 'vue'

defineProps({
    customClass: {
        type: String
    },
    imageUrl: {
        type: String,
        default: 'https://www.gravatar.com/avatar/?d=mp'
    }
})

const emit = defineEmits(['click'])

type ClickHandler = () => void

const handleClick: ClickHandler = () => {
    emit('click')
}
</script>

<template>
    <div :class="customClass" @click="handleClick">
        <img :src="imageUrl" alt="Avatar" />
    </div>
</template>

<style scoped></style>
