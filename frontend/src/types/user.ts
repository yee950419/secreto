import type { Ref } from 'vue'

export type MyPageUserDataType = Ref<{
    email: string
    nickname: string
    profileUrl: string | null
}>
