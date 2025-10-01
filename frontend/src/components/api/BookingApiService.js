import axios from "axios"

export const apiClient = axios.create({
    baseURL: 'http://localhost:8082'
})

export const getBookingsForUser = () => apiClient.get(`booking`)

export const addBookingForUser = (booking) => apiClient.post(`booking/request`, booking)