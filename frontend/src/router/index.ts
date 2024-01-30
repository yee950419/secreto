import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            name: 'beforeLogin',
            component: () => import('@/views/LoginView.vue'),
            meta: {
                hide: true
            }
        },
        {
            path: '/main',
            name: 'main',
            component: () => import('@/views/MainView.vue'),
            meta: {
                hide: true
            }
        },
        {
            path: '/game',
            name: 'game',
            component: () => import('@/components/template/TimeLine.vue')
        },
        {
            path: '/board',
            name: 'board',
            component: () => import('@/views/BoardView.vue')
        },
        {
            path: '/chat',
            name: 'chat',
            component: () => import('@/views/ChattingView.vue')
        },
        {
            path: '/info',
            name: 'info',
            component: () => import('@/views/ServiceView.vue')
        }
    ]
})

export default router
