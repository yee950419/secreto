import { defineStore } from 'pinia'
import { ref } from 'vue'
import { login } from '@/api/user'
import type { LoginRequestType } from '@/types/user'
type UserInterface = {
    email: string
    userName: string
    profileUrl: string
    provider: string
}

export const useUserStore = defineStore(
    'userStore',
    () => {
        // 전역 관리가 필요한 데이터에 대한 정의
        const isLogin = ref<boolean>(false) // 로그인 여부 검증
        const userInfo = ref<UserInterface>({
            email: '',
            userName: '',
            profileUrl: '',
            provider: ''
        }) // 사용자의 정보 저장
        const accessToken = ref<string>('') // 사용자의 엑세스토큰 저장
        const refreshToken = ref<string>('') // 사용자의 리프레시 토큰 저장

        const userLogin = async (loginRequest: LoginRequestType) => {
            console.log(loginRequest)
            await login(
                loginRequest,
                (response) => {
                    const data = response.data.result
                    accessToken.value = data.accessToken
                    refreshToken.value = data.refreshToken
                    //    getUserInfo(loginRequest.email)
                },
                (error) => {
                    console.log(error)
                    alert(error.response.data.message)
                }
            )
        }

        // const getUserInfo = async (email: string) => {
        //     await getUser(
        //         email,
        //         (response) => {
        //             userInfo.value = response.data.result.userInfo
        //         },
        //         (error) => console.log(error)
        //     )
        // }
        return { isLogin, userInfo, accessToken, refreshToken, userLogin }
    },
    { persist: true }
)
