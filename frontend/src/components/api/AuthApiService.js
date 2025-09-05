import apiClient from "./ApiClientService"

export const executeJwtAuthenticationService = (username, password) => apiClient.post(`/authenticate`, {
    username,
    password
})