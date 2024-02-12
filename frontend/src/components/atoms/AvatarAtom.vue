<script setup lang="ts">
import type { Handler } from '@/types/common'
import defaultImage from '@/assets/images/default-avatar.png'
const { imageUrl, customClass } = defineProps({
    customClass: {
        type: String
    },
    imageUrl: {
        type: String as () => string | null | undefined
    }
})

const emit = defineEmits(['imageClick'])
const handleClick: Handler = () => {
    emit('imageClick')
}
</script>

<template>
    <div :class="customClass" @click="handleClick">
        <img :src="imageUrl ? imageUrl : defaultImage" alt="Avatar" class="w-full h-full object-fill" />
    </div>
</template>

<style scoped></style>

