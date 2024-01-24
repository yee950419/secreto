import axios from 'axios'
const { VITE_API_BASE_URL } = import.meta.env

function localAxios() {
    const instance = axios.create({
        baseURL: VITE_API_BASE_URL,
        headers: {
            'Content-Type': 'application/json'
        }
    })

    return instance
}

export { localAxios }
