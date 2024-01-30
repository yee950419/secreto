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
    replyCount: number
}
