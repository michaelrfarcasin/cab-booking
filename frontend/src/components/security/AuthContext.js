import { createContext, useContext, useState } from "react";

export const AuthContext = createContext()

export const useAuth = () => useContext(AuthContext)

const AuthProvider = ({ children }) => {
    const [isAuthenticated, setAuthenticated] = useState(false)
    const [username, setUsername] = useState('')

    const login = (username, password) => {
        if (true) {//TODO
            setAuthenticated(true)
            setUsername(username)
            return true
        }
        setAuthenticated(false)
        setUsername('')
        return false
    }

    const logout = () => setAuthenticated(false)

    return (
        <AuthContext.Provider value={{isAuthenticated, login, logout, username}}>
            {children}
        </AuthContext.Provider>
    )
}

export default AuthProvider;