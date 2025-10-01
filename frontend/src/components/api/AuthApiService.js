import axios from "axios"

export const apiClient = axios.create({
    baseURL: 'http://localhost:8081'
})

export const executeJwtAuthenticationService = (username, password) => apiClient.post(`/user/generateToken`, {
    username,
    password
})