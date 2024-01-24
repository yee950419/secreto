<script setup lang="ts">
import { RouterView } from 'vue-router'
import HeaderBar from '@/components/organisms/HeaderBar.vue'
import FooterBar from '@/components/organisms/FooterBar.vue'
</script>

<template>
    <div class="flex flex-1 flex-col w-screen h-screen">
        <HeaderBar v-if="$route.meta.hide !== true" />
        <RouterView />
        <FooterBar v-if="$route.meta.hide !== true" />
    </div>
</template>

<style scoped></style>
