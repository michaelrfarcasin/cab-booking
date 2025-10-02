import axios from "axios"

const AUTH_API_URL = process.env.REACT_APP_AUTH_API_URL || 'http://localhost:8081'

export const apiClient = axios.create({
    baseURL: AUTH_API_URL
})

export const executeJwtAuthenticationService = (username, password) => apiClient.post(`/user/generateToken`, {
    username,
    password
})