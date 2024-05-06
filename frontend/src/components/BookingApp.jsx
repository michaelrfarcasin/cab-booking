import './BookingApp.css'
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import LoginComponent from './LoginComponent'
import LogoutComponent from './LogoutComponent'
import HomeComponent from './HomeComponent'
import ErrorComponent from './ErrorComponent'
import HeaderComponent from './HeaderComponent'
import BookingComponent from './BookingComponent'
import AuthProvider, { useAuth } from './security/AuthContext'

const AuthenticatedRoute = ({ children }) => {
    const authContext = useAuth()
    
    if (authContext.isAuthenticated) {
        return children
    }

    return <Navigate to="/login" />
}

const BookingApp = () => {
    return (
        <div className="BookingApp">
            <AuthProvider>
                <BrowserRouter>
                    <HeaderComponent />
                    <Routes>
                        <Route path='/login' element={ <LoginComponent /> } ></Route>
                        <Route path='/logout' element={ 
                            <AuthenticatedRoute><LogoutComponent /></AuthenticatedRoute>
                        } ></Route>
                        <Route path='/' element={ 
                            <AuthenticatedRoute><HomeComponent /></AuthenticatedRoute>
                        } ></Route>
                        <Route path='/booking' element={ 
                            <AuthenticatedRoute><BookingComponent /></AuthenticatedRoute>
                        } ></Route>
                        <Route path='*' element={ <ErrorComponent /> } ></Route>
                    </Routes>
                </BrowserRouter>
            </AuthProvider>
        </div>
    )
}

export default BookingApp;