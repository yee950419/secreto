import { defineStore } from 'pinia'
import { ref } from 'vue'
import { login, getUser, logout } from '@/api/user'
import type { LoginRequestType, LogoutRequestType } from '@/types/user'
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
            id: -1,
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

        const clearUserStore = () => {
            isLogin.value = false
            userInfo.value.id = -1
            userInfo.value.email = ''
            userInfo.value.nickname = ''
            userInfo.value.profileUrl = ''
            userInfo.value.provider = ''
            accessToken.value = ''
            refreshToken.value = ''
            viewState.value = ViewState.MAIN
        }

        const getUserToStore = () => {
            getUser(
                (response) => {
                    const data = response.data
                    console.log(response)
                    if (data.status === 'OK') {
                        isLogin.value = true
                        userInfo.value = data.result
                    }
                },
                (error) => {
                    console.log(error)
                    alert(error.response.data.message)
                }
            )
        }

        const userLogout = () => {
            logout(
                { email: userInfo.value.email, provider: userInfo.value.provider },
                (response) => {
                    const data = response.data
                    console.log(response)
                    if (data.status === 'OK') {
                        clearUserStore()
                        router.push({ name: 'beforeLogin' })
                    }
                },
                (error) => {
                    console.log(error)
                    alert(error.response.data.message)
                }
            )
        }

        return {
            isLogin,
            userInfo,
            accessToken,
            refreshToken,
            viewState,
            userLogin,
            getUserToStore,
            clearUserStore,
            userLogout
        }
    },
    { persist: true }
)
