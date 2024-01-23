<script setup lang="ts">
import { defineProps, defineEmits } from 'vue'

const { customClass, customId } = defineProps(['customClass','customId'])
const emit = defineEmits()
</script>

<template>
    <div :class="customClass">
      <input type="checkbox" :id="customId" />
      <label :for="customId"><slot></slot></label>
    </div>  
</template>




<style scoped></style>
  