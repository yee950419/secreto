import type { AxiosInstance, AxiosResponse } from 'axios'
import { localAxios } from '@/utils/http-commons'
const historyInstance: AxiosInstance = localAxios()

async function getPredictResult(
    param: string,
    success: (response: AxiosResponse) => void,
    fail: (error: any) => void
) {
    await historyInstance.get(`/history/${param}/predict`).then(success).catch(fail)
}

async function getWordCloud(
    param: number,
    success: (response: AxiosResponse) => void,
    fail: (error: any) => void
) {
    await historyInstance.get(`/history/${param}/wordCloud`).then(success).catch(fail)
}

async function sendWord(
    param: number,
    text : string,
    success: (response: AxiosResponse) => void,
    fail: (error: any) => void
) {
    await historyInstance.post(`/history/${param}/wordCloud`, {contents: text}).then(success).catch(fail)
}


export { getPredictResult, getWordCloud, sendWord }
