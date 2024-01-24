import type { AxiosInstance, AxiosResponse } from 'axios'
import { localAxios } from '@/utils/http-commons'
const boardInstance: AxiosInstance = localAxios()

async function getBoard(
    param: object,
    success: (response: AxiosResponse) => void,
    fail: (error: any) => void
) {
    boardInstance.get(`/board`, { params: param }).then(success).catch(fail)
}

export { getBoard }
