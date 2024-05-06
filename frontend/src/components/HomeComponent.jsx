import { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import ListBookingsComponent from './ListBookingsComponent'
import { getBookingsForUser } from './api/BookingApiService'
import { useAuth } from './security/AuthContext'

const HomeComponent = () => {
    const [bookings, setBookings] = useState([])

    const authContext = useAuth()
    const username = authContext.username
    const navigate = useNavigate()

    useEffect(() => {
        getBookingsForUser(username)
            .then((response) => setBookings(response.data))
            .catch((error) => console.log(error))
    }, [username])

    return (
        <div className="container">
            <h1>Your rides</h1>
            <div>
                {bookings.length === 0 ? "You haven't booked any rides." :
                    <ListBookingsComponent bookings={bookings} />}
            </div>
            <button type="button" onClick={() => navigate('/booking')}>Book a ride</button>
        </div>
    )
}

export default HomeComponent;