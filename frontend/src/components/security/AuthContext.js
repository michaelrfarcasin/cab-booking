import { createContext, useContext, useState } from "react";
import { executeJwtAuthenticationService } from "../api/AuthApiService";
import { HttpStatusCode } from "axios";
import apiClient from "../api/ApiClientService";

export const AuthContext = createContext()

export const useAuth = () => useContext(AuthContext)

const AuthProvider = ({ children }) => {
    const [isAuthenticated, setAuthenticated] = useState(false)
    const [username, setUsername] = useState('')

    const login = async (username, password) => {
        try {
            const response = await executeJwtAuthenticationService(username, password)
            if (response.status === HttpStatusCode.Ok) {
                setAuthenticated(true)
                setUsername(username)
                const authToken = 'Bearer ' + response.data
                apiClient.interceptors.request.use(
                    (config) => {
                        config.headers.Authorization = authToken
                        return config
                    }
                )
                return true
            } else {        
                logout()
                return false
            }
        } catch (error) {
            console.error(error)
            logout()
            return false
        }
    }

    const logout = () => {
        setAuthenticated(false)
        setUsername('')
    }

    return (
        <AuthContext.Provider value={{isAuthenticated, login, logout, username}}>
            {children}
        </AuthContext.Provider>
    )
}

export default AuthProvider;