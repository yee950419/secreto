import { createRouter, createWebHistory } from 'vue-router'
import MainPageView from '@/views/MainPageView.vue'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            component: MainPageView
        },
        {
            path: '/game',
            name: 'game',
            component: () => import('@/views/HomeView.vue')
        }
    ]
})

export default router
