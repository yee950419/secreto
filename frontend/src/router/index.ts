import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import MainPageView from '@/views/MainPageView.vue'
import sswTestVue from '@/views/sswTest.vue'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            component: () => import('@/views/MainPageView.vue'),
            meta: {
                hide: true
            }
        },
        {
            path: '/game',
            name: 'game',
            component: () => import('@/components/template/TimeLine.vue')
        }
    ]
})

export default router
