import sswTestVue from '@/components/pages/sswTest.vue'
import { createRouter, createWebHistory } from 'vue-router'
import { useUserStore } from '@/stores/user'
import { storeToRefs } from 'pinia'
import RoomView from '@/components/pages/RoomView.vue'
const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            name: 'beforeLogin',
            component: () => import('@/components/pages/LoginView.vue'),
            meta: {
                hide: true
            },
            // 로그인이 된 상태라면 메인으로 이동
            beforeEnter(to, from, next) {
                const userStore = useUserStore()
                const { accessToken, refreshToken } = storeToRefs(userStore)

                // 토큰이 있으면 로그인 상태로 간주 한다
                if (accessToken.value && refreshToken.value) {
                    next('/main')
                } else {
                    next()
                }
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
            path: '/game/:roomId',
            name: 'game',
            component: RoomView,
            children: [
                {
                    path: 'board',
                    name: 'game-board',
                    component: () => import('@/components/template/board/BoardList.vue')
                },
                {
                    path: 'mission',
                    name: 'game-mission',
                    component: () => import('@/components/pages/UserMission.vue')
                },
                {
                    path: 'participate',
                    name: 'game-participate',
                    component: () => import('@/components/pages/ParticipatePage.vue')
                },
                {
                    path: 'timeline',
                    name: 'game-timeline',
                    component: () => import('@/components/pages/TimeLinePage.vue')
                },
                {
                    path: 'review',
                    name: 'game-review',
                    component: () => import('@/components/pages/ReviewPage.vue')
                },
                {
                    path: 'statistic',
                    name: 'game-statistic',
                    component: () => import('@/components/pages/StatisticPage.vue')
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
