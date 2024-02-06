import type { AxiosInstance, AxiosResponse } from 'axios'
import { localAxios } from '@/utils/http-commons'
const roomInstance: AxiosInstance = localAxios()

async function createRoom(
    param: object,
    success: (response: AxiosResponse) => void,
    fail: (error: any) => void
) {
    roomInstance.post(`/room`, JSON.stringify(param)).then(success).catch(fail)
}

async function getRoom(
    param: number,
    success: (response: AxiosResponse) => void,
    fail: (error: any) => void
) {
    roomInstance.get(`/room/${param}`).then(success).catch(fail)
}

async function changeRoomName(
    param: { roomName: string; roomNo: number },
    success: (response: AxiosResponse) => void,
    fail: (error: any) => void
) {
    roomInstance.put(`/room`, param).then(success).catch(fail)
}

async function getRoomList(success: (response: AxiosResponse) => void, fail: (error: any) => void) {
    roomInstance.get(`/room/user/user_room`).then(success).catch(fail)
}

async function getUserList(
    param: number,
    success: (response: AxiosResponse) => void,
    fail: (error: any) => void
) {
    roomInstance.get(`/room/user/${param}`).then(success).catch(fail)
}

async function acceptRoomUsers(
    param: Array<{ roomUserNo: number }>,
    success: (response: AxiosResponse) => void,
    fail: (error: any) => void
) {
    roomInstance.put(`/room/accept`, param).then(success).catch(fail)
}

export { createRoom, getRoom, changeRoomName, getUserList, getRoomList, acceptRoomUsers }
