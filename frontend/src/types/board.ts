import { useRoute } from 'vue-router'

export type BoardRequestType = {
    boardCategory: string
    title: string | null
    content: string | null
    writer: string | null
    page: number
    size: number
}

export type BoardResponseType = {
    boardNo: number
    title: string
    writer: string
    writerEmail: string
    writerProfileUrl: string | null
    registerAt: string
    hit: number
    boardCategory: string
    publicYn: boolean
    missionCategory: string | null
    likedCount: number
    replyCount: number
}

export type BoardDetailResponseType = {
    boardNo: number
    roomUserNo: number
    title: string
    content: string
    writer: string
    writerEmail: string
    writerProfileUrl: string | null
    registerAt: string
    hit: number
    boardCategory: string
    publicYn: boolean
    missionCategory: string | null
    likedCount: number
}

export type ReplyResponseType = {
    replyNo: number
    content: string
    registerAt: string
    parentReplyNo: number | null
    tagUserNickname: string | null
    roomUserNo: number
    writer: string
    writerEmail: string
    writerProfileUrl: string | null
    children?: ReplyResponseType[] | undefined
    anonymityYn?: boolean
    deleteYn?: boolean
}

export type ReplyModifyRequestType = {
    content: string
    anonymityYn: boolean
}

export type ReplyWriteRequestType = {
    boardNo: number // boardNo?? postNo
    roomUserNo: number
    content: string // api에서 오타 수정 후 원복
    parentReplyNo?: number
    tagUserNo?: number
    annonymityYn?: boolean
}

export type BoardWriteRequestType = {
    title: string
    content: string
    imageUrl: string | null
    boardCategory: string
    publicYn: boolean
    missionCategory: string | null
}

export const BoardCategory = Object.freeze({
    BOAST: 'BOAST',
    CERTIFICATE: 'CERTIFICATE',
    NOTICE: 'NOTICE'
})
