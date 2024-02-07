import type { AxiosInstance, AxiosResponse } from 'axios'
import { localAxios } from '@/utils/http-commons'
const missonInstance: AxiosInstance = localAxios()

async function getSystemMission(
    success: (response: AxiosResponse) => void,
    fail: (error: any) => void
) {
    missonInstance.get(`/mission`).then(success).catch(fail)
}

async function getSuddenMission(
    roomNo: number,
    success: (response: AxiosResponse) => void,
    fail: (error: any) => void
) {
    missonInstance.get(`/mission/sudden/${roomNo}`).then(success).catch(fail)
}

async function addUnexpectedMission(
    param: {
        roomNo: number
        content: string
        missionSubmitAt?: string
    },
    success: (response: AxiosResponse) => void,
    fail: (error: any) => void
) {
    missonInstance.post(`/mission/sudden`, param).then(success).catch(fail)
}

async function getUserMission(
    roomNo: number,
    success: (response: AxiosResponse) => void,
    fail: (error: any) => void
) {
    missonInstance.get(`/mission/user/${roomNo}`).then(success).catch(fail)
}

async function getRoomMission(
    roomNo: number,
    success: (response: AxiosResponse) => void,
    fail: (error: any) => void
) {
    missonInstance.get(`/mission/${roomNo}`).then(success).catch(fail)
}

export { getSystemMission, getSuddenMission, getUserMission, getRoomMission, addUnexpectedMission }
