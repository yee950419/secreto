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

// 초대코드로 룸 검증 
async function checkRoom(
    param : string,
    success: (response: AxiosResponse) => void,
    fail: (error: any) => void
)
{
    roomInstance.post(`/room/search`, {'entryCode' : param}).then(success).catch(fail)
}

async function enterRoom(
    param: object,
    success: (response: AxiosResponse) => void,
    fail: (error: any) => void
)
{
    console.table(param)
    roomInstance.post(`/room/enter`, param).then(success).catch(fail)
}

async function getRoom(
    param: number,
    success: (response: AxiosResponse) => void,
    fail: (error: any) => void
) {
    roomInstance.get(`/room/${param}`).then(success).catch(fail)
}

async function changeRoomName(
    param: object,
    success: (response: AxiosResponse) => void,
    fail: (error: any) => void
) {
    roomInstance.put(`/room/`, JSON.stringify(param)).then(success).catch(fail)
}

async function getRoomList(success: (response: AxiosResponse) => void, fail: (error: any) => void) {
    roomInstance.get(`/room/user/user_room`).then(success).catch(fail)
}

async function checkFavorite(
    param: number,
    success: (response: AxiosResponse) => void,
    fail: (error: any) => void
) {
    roomInstance.put(`/room/bookmark`, {roomNo : param}).then(success).catch(fail)
}


async function getUserList(
    param: number,
    success: (response: AxiosResponse) => void,
    fail: (error: any) => void
) {
    roomInstance.get(`/room/user/${param}`).then(success).catch(fail)
}

async function exitRoom(
    param: number,
    success: (response: AxiosResponse) => void,
    fail: (error: any) => void
) {
    roomInstance.put(`/room/exit`, {roomNo : param}).then(success).catch(fail)
}

export { createRoom, getRoom, changeRoomName, getUserList, getRoomList, checkRoom, enterRoom, exitRoom, checkFavorite }
