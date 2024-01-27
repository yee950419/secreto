<script setup lang="ts">
defineProps(['customClass'])
</script>

<template>
    <div class="rounded-md px-[5px] py-[3px]" :class="customClass"><slot></slot></div>
</template>

<style scoped></style>
