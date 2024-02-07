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
    missionType: 'SUDDEN' | 'REGULAR'
    missionRerollCount: number
    missionCertifyYn: boolean
}

export type SuddenMissionResponse = {
    content: string
    missionSubmitAt: string
}
