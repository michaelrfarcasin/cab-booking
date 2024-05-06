import apiClient from "./ApiClientService"

export const getBookingsForUser = (name) => apiClient.get(`booking/user/${name}`)

export const addBookingForUser = (name, booking) => apiClient.post(`booking/request/${name}`, booking)