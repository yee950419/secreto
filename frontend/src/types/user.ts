import type { Ref } from 'vue'

export type MyPageUserDataType = Ref<{
    email: string
    nickname: string
    profileUrl: string | null
}>

export type LoginRequestType = Ref<{
    email: string
    password: string
}>

export type JoinRequestType = Ref<{
    email: string
    password: string
    nickname: string
}>

export type PasswordChangeRequest = Ref<{
    currentPassword: string
    newPassword: string
}>

export type PasswordFindMailRequest = Ref<{
    email: string
}>
