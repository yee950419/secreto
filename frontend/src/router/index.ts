import sswTestVue from '@/components/pages/sswTest.vue'
import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            name: 'beforeLogin',
            component: () => import('@/components/pages/LoginView.vue'),
            meta: {
                hide: true
            }
        },
        {
            path: '/main',
            name: 'main',
            component: () => import('@/components/pages/MainView.vue'),
            meta: {
                hide: true
            }
        },
        {
            path: '/game/:roomNo',
            name: 'game',
            component: () => import('@/components/pages/RoomView.vue'),
            children: [
                {
                    path: 'board',
                    name: 'game-board',
                    component: () => import('@/components/template/board/BoardList.vue'),
                    props: true
                },
                {
                    path: 'post',
                    name: 'game-board-post',
                    component: () => import('@/components/template/board/BoardDetail.vue'),
                    props: true
                },
                {
                    path: 'mission',
                    name: 'game-mission',
                    component: sswTestVue
                },
                {
                    path: 'participate',
                    name: 'game-participate',
                    component: () => import('@/components/pages/ParticipateView.vue')
                }
            ]
        },
        {
            path: '/board',
            name: 'board',
            component: () => import('@/components/pages/BoardView.vue')
        },
        {
            path: '/chat',
            name: 'chat',
            component: () => import('@/components/pages/ChattingView.vue')
        },
        {
            path: '/info',
            name: 'info',
            component: () => import('@/components/pages/ServiceView.vue')
        },
        {
            path: '/roomsettings',
            name: 'roomsettings',
            component: () => import('@/components/pages/RoomSetting.vue'),
            meta: {
                hide: true
            }
        }
    ]
})

export default router
