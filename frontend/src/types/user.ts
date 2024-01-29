export type MyPageUserDataType = {
    email: string
    nickname: string
    profileUrl: string | null
}

export type LoginRequestType = {
    email: string
    password: string
}

export type JoinRequestType = {
    email: string
    password: string
    nickname: string
}

export type PasswordChangeRequest = {
    currentPassword: string
    newPassword: string
}

export type PasswordFindMailRequest = {
    email: string
}

export type ProfileInfoType = {
    id: number
    nickname: string
    profileUrl: string
    email: string
}
