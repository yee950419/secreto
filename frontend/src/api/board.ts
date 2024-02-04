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

async function getPost(
    postNo: number,
    success: (response: AxiosResponse) => void,
    fail: (error: any) => void
) {
    boardInstance.get(`/post/${postNo}`).then(success).catch(fail)
}

async function getReplies(
    postNo: number,
    success: (response: AxiosResponse) => void,
    fail: (error: any) => void
) {
    boardInstance.get(`/reply/${postNo}`).then(success).catch(fail)
}

async function postReply(
    postNo: number,
    param: object,
    success: (response: AxiosResponse) => void,
    fail: (error: any) => void
) {
    boardInstance.post(`/reply/${postNo}`, param).then(success).catch(fail)
}

async function deleteReply(
    replyNo: number,
    success: (response: AxiosResponse) => void,
    fail: (error: any) => void
) {
    boardInstance
        .delete(`/reply/${replyNo}`, { params: { replyNo: replyNo } })
        .then(success)
        .catch(fail)
}

export { getBoard, getPost, getReplies, postReply, deleteReply }
