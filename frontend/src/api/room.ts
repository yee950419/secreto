import type { AxiosInstance } from 'axios'
import { localAxios } from '@/utils/http-commons'
const room: AxiosInstance = localAxios()

function getRoom(param: number, success: (response) => void, fail: (error: any) => void) {
    room.get(`/room/${param}`).then(success).catch(fail)
}

export { getRoom }
