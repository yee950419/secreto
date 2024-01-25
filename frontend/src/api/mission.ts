import type { AxiosInstance, AxiosResponse } from 'axios'
import { localAxios } from '@/utils/http-commons'
const missonInstance: AxiosInstance = localAxios()

async function getSystemMission(
    success: (response: AxiosResponse) => void,
    fail: (error: any) => void
) {
    missonInstance.get(`/mission`).then(success).catch(fail)
}

export { getSystemMission }
