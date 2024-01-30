<script setup lang="ts">
import { RouterView } from 'vue-router'
import HeaderBar from '@/components/organisms/HeaderBar.vue'
import FooterBar from '@/components/organisms/FooterBar.vue'

// const handleServerSentEvent = () => {
//     const eventSource = new EventSource(VITE_API_BASE_URL + '/alarm') // 서버 엔드포인트를 적절히 설정해야 합니다.

//     eventSource.addEventListener('message', (event) => {
//         console.log('Server Sent Event received:', event.data)
//         // 여기에서 서버에서 전송된 이벤트를 처리합니다.
//     })

//     eventSource.addEventListener('error', (event) => {
//         console.error('Server Sent Event error:', event)
//         // 여기에서 오류 처리를 수행합니다.
//     })
// }

// onMounted(() => {
//     handleServerSentEvent()
// })
</script>

<template>
    <div class="flex flex-1 flex-col w-screen h-screen overflow-x-hidden">
        <HeaderBar v-if="$route.meta.hide !== true" />
        <RouterView />
        <FooterBar v-if="$route.meta.hide !== true" />
    </div>
</template>

<style scoped></style>
