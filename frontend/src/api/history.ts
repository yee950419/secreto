import type { AxiosInstance, AxiosResponse } from 'axios'
import { localAxios } from '@/utils/http-commons'
const historyInstance: AxiosInstance = localAxios()

async function getPredictResult(
    param: string,
    success: (response: AxiosResponse) => void,
    fail: (error: any) => void
) {
    historyInstance.get(`/history/${param}/predict`).then(success).catch(fail)
}

export { getPredictResult }
