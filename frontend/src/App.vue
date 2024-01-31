<script setup lang="ts">
import { RouterView } from 'vue-router'
import HeaderBar from '@/components/organisms/HeaderBar.vue'
import FooterBar from '@/components/organisms/FooterBar.vue'
import { ref, watch, onMounted } from 'vue'
import { useRoute } from 'vue-router'
const headerSeen = ref(false)
const route = useRoute()

watch(route, () => {
    if (route.meta.hide === true) {
        headerSeen.value = false
    } else {
        headerSeen.value = true
    }
})


</script>

<template>
    <div class="flex flex-1 flex-col w-screen h-screen">
        <HeaderBar v-if="headerSeen" />
        <RouterView />
        <FooterBar v-if="headerSeen" />
    </div>
</template>

<style scoped></style>
