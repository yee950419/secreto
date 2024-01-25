import type { Ref } from 'vue'

export type MyPageUserDataType = Ref<{
    email: string
    nickname: string
    profileUrl: string | null
}>

export type PasswordChangeRequest = Ref<{
    currentPassword: string
    newPassword: string
}>
