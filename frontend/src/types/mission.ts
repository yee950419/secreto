export type Mission = {
    id?: number
    content: string
    checked?: boolean
}

export type RoomMission = {
    content: string
}

export type UserMission = {
    content: string
    missionReceivedAt: string
    missionType: 'common' | 'individual'
    missionRerollCount: number
    missionCertifyYn: boolean
}
