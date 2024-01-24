<script setup lang="ts">
defineProps({
    customClass: {
        type: String
    },
    imageUrl: {
        type: String as () => string | null,
        required: true
    }
})

const emit = defineEmits(['imageClick'])

type ClickHandler = () => void

const handleClick: ClickHandler = () => {
    emit('imageClick')
}
</script>

<template>
    <div :class="customClass" @click="handleClick">
        <img :src="imageUrl ? imageUrl : 'src/assets/images/default-avatar.png'" alt="Avatar" />
    </div>
</template>

<style scoped></style>
