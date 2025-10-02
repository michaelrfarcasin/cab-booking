import axios from "axios"

const BOOKING_API_URL = process.env.REACT_APP_BOOKING_API_URL || 'http://localhost:8082'

export const apiClient = axios.create({
    baseURL: BOOKING_API_URL
})

export const getBookingsForUser = () => apiClient.get(`booking`)

export const addBookingForUser = (booking) => apiClient.post(`booking/request`, booking)