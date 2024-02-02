import { defineStore } from 'pinia'
import { ref } from 'vue'
import { login } from '@/api/user'
import type { LoginRequestType } from '@/types/user'
import { useRouter } from 'vue-router'

type UserInterface = {
    id: number
    email: string
    nickname: string
    profileUrl: string
    provider: string
}

export const ViewState = Object.freeze({
    MAIN: 'main',
    LOGIN: 'login',
    JOIN: 'join',
    PASSWORD: 'find_password',
    TEMPLATE: 'template'
})

export const useUserStore = defineStore(
    'userStore',
    () => {
        // 전역 관리가 필요한 데이터에 대한 정의
        const isLogin = ref<boolean>(false) // 로그인 여부 검증
        const userInfo = ref<UserInterface>({
            id: 0,
            email: '',
            nickname: '',
            profileUrl: '',
            provider: ''
        }) // 사용자의 정보 저장
        const accessToken = ref<string>('') // 사용자의 엑세스토큰 저장
        const refreshToken = ref<string>('') // 사용자의 리프레시 토큰 저장
        const viewState = ref<string>(ViewState.MAIN)
        const router = useRouter()

        const userLogin = (loginRequest: LoginRequestType) => {
            login(
                loginRequest,
                (response) => {
                    const data = response.data.result
                    isLogin.value = true
                    accessToken.value = data.accessToken
                    refreshToken.value = data.refreshToken
                    userInfo.value = data.userInfo
                    console.log(response.data.message)
                    router.push({ name: 'main' })
                },
                (error) => {
                    console.log(error)
                    alert(error.response.data.message)
                }
            )
        }
        return { isLogin, userInfo, accessToken, refreshToken, viewState, userLogin }
    },
    { persist: true }
)
