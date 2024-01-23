<script setup lang="ts">
import { defineProps, defineEmits } from 'vue'

const { customClass, customId } = defineProps(['customClass','customId'])
const emit = defineEmits()
</script>

<template>
    <div :class="customClass" :id="customId">
      <input type="checkbox" id="checkboxId" />
      <label for="checkboxId"><slot></slot></label>
    </div>  
</template>




<style scoped></style>
  