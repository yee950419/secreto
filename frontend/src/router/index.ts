import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import MainPageView from '../views/MainPageView.vue'
import sswTestVue from '../views/sswTest.vue'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            component: sswTestVue
        },
        {
            path: '/about',
            name: 'about',
            component: () => import('../views/AboutView.vue')
        },
        {
            path: '/sswtest',
            name: 'ssw-Test',
            component: () => import('../views/sswTest.vue')
        },
    ]
})

export default router
