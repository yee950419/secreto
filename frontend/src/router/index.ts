import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
    history: createWebHistory(),
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
