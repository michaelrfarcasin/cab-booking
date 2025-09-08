import apiClient from "./ApiClientService"

export const executeJwtAuthenticationService = (username, password) => apiClient.post(`/user/generateToken`, {
    username,
    password
})