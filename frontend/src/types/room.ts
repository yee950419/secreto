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

export type RoomListInfoType = {
    bookmarkYn: boolean
    commonYn: string
    entryCode: string
    hostParticipateYn: string
    hostUserNo : number
    missionSubmitTime: string
    missionStartAt: string
    nickname: string
    participantCnt: number
    roomEndAt: string
    roomName: string
    roomNo: number
    roomStartAt: string
    roomStartYn: boolean
    standbyYn: boolean
    roomStatus: string
}

export type RoomInfoType = {
    entryCode: string
    commonYn: string
    hostParticipateYn: string
    hostRoomUserNo: number
    missionSubmitTime: string
    missionStartAt: string
    roomEndAt: string
    roomName: string
    roomNo: number
    roomStartAt: string
    roomStartYn: string
    roomStatus: string
    userInfo: {
        nickname : string,
        profileUrl: string
        roomUserNo : number
    }
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
