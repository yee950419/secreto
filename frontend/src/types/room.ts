export type RoomCreateRequestType = {
    roomName: string
    hostNickname: string
}
export type RoomInfoTypeTest = {
    roomNo: number
    title: string
    nickname: string
    peopleNumber: number
    like: boolean
    roomStartAt: string
    roomEndAt: string
    roomStatus?: string
}

export type RoomInfoType = {
    roomNo: number
    roomName: string
    entryCode: string
    roomStartAt: string
    roomEndAt: string
    hostParticipateYn: string
    commonYn: string
    missionSubmitTime: string
    missionStartAt: string
    roomStartYn: string
    standbyYn: string
    nickname: string
    participantCnt: number
    bookmarkYn: boolean
}

export type RoomUserInfoType = {
    roomNo: number
    roomUserNo: number
    roomName: string
    roomNickname: string
    profileUrl: string
}

export type userType = {
    userNo: number
    userLeaveAt: string
    userEntryAt: string
    standbyYn: boolean
    roomUserNo: number
    roomNo: number
    profile_url: string
    nickname: string
}
