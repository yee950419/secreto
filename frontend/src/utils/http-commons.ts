import axios from 'axios'
const { VITE_API_BASE_URL } = import.meta.env

import { HttpStatusCode } from 'axios'

function localAxios() {
    const instance = axios.create({
        baseURL: VITE_API_BASE_URL
    })

    return instance
}

export { localAxios }
