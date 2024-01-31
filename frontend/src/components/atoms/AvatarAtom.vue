<script setup lang="ts">
import type { Handler } from '@/types/common'

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
        <img :src="imageUrl ? imageUrl : '/src/assets/images/default-avatar.png'" alt="Avatar" />
    </div>
</template>

<style scoped></style>
