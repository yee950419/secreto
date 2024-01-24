<script setup lang="ts">
import { ref, computed } from 'vue'
import { RouterLink, RouterView } from 'vue-router'
import HeaderBar from '@/components/organisms/HeaderBar.vue'
import FooterBar from '@/components/organisms/FooterBar.vue'

const showNav = computed(() => {
    // 현재 라우트의 메타 데이터에서 헤더 표시 여부 확인
    return $route.meta.showNav !== false || true
})
</script>

<template>
    <div>
        <HeaderBar v-if="false" />
        <RouterView />
        <FooterBar v-if="false" />
    </div>
</template>

<style scoped></style>
