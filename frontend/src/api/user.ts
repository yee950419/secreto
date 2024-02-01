import type { AxiosInstance, AxiosResponse } from 'axios'
import { localAxios } from '@/utils/http-commons'

const userInstance: AxiosInstance = localAxios()

async function login(
    param: object,
    success: (response: AxiosResponse) => void,
    fail: (error: any) => void
) {
    userInstance.post(`/users/log-in`, JSON.stringify(param)).then(success).catch(fail)
}

async function logout(
    param: object,
    success: (response: AxiosResponse) => void,
    fail: (error: any) => void
) {
    userInstance.post(`/users/log-out`, JSON.stringify(param)).then(success).catch(fail)
}

async function signup(
    param: object,
    success: (response: AxiosResponse) => void,
    fail: (error: any) => void
) {
    userInstance.post(`/users/sign-up`, JSON.stringify(param)).then(success).catch(fail)
}

async function getUser(
    param: string,
    success: (response: AxiosResponse) => void,
    fail: (error: any) => void
) {
    userInstance.get(`/users/${param}`).then(success).catch(fail)
}

async function withdraw(
    param: object,
    success: (response: AxiosResponse) => void,
    fail: (error: any) => void
) {
    userInstance.put(`/users/delete`, JSON.stringify(param)).then(success).catch(fail)
}

async function modify(
    param: object,
    success: (response: AxiosResponse) => void,
    fail: (error: any) => void
) {
    userInstance.put(`/users/modify`, JSON.stringify(param)).then(success).catch(fail)
}

async function userDetail(
    param: string,
    success: (response: AxiosResponse) => void,
    fail: (error: any) => void
) {
    userInstance.get(`/users/${param}`).then(success).catch(fail)
}

async function changePassword(
    param: object,
    success: (response: AxiosResponse) => void,
    fail: (error: any) => void
) {
    userInstance.post(`/users/password/request`, JSON.stringify(param)).then(success).catch(fail)
}

async function changePasswordConfirm(
    certCode: string,
    success: (response: AxiosResponse) => void,
    fail: (error: any) => void
) {
    userInstance.post(`/users/password/${certCode}`).then(success).catch(fail)
}

async function resetPassword(
    password: string,
    userId: string,
    success: (response: AxiosResponse) => void,
    fail: (error: any) => void
) {
    userInstance.post(`/users/change/reset/${userId}`, password).then(success).catch(fail)
}

async function regenerateToken(
    param: object,
    success: (response: AxiosResponse) => void,
    fail: (error: any) => void
) {
    userInstance.get(`/users/token/regenerate`).then(success).catch(fail)
}

export {
    getUser,
    login,
    logout,
    signup,
    withdraw,
    modify,
    changePassword,
    userDetail,
    changePasswordConfirm,
    resetPassword,
    regenerateToken
}
