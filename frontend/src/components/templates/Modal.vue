<script setup lang="ts">
import { defineProps, defineEmits } from 'vue'
import { CloseOutlined } from '@ant-design/icons-vue'
const { customClass, customId, seen } = defineProps(['customClass','customId', 'seen'])

</script>

<template>
    <div v-if="seen"
    class="w-full h-full fixed top-0 left-0 flex justify-center items-center bg-A805DarkGrey bg-opacity-20"
    >
        <div :class="customClass">
            <div class="flex w-full justify-end">
                <CloseOutlined
                class="cursor-pointer"
                style="font-size:32px; color:#e0aed0"
                />
            </div>
            <div class="flex w-full flex-col items-center">
                <slot></slot>
            </div>
        </div>
    </div>
</template>

<style scoped></style>
