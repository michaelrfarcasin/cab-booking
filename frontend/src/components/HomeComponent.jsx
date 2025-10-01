import { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import ListBookingsComponent from './ListBookingsComponent'
import { getBookingsForUser } from './api/BookingApiService'

const HomeComponent = () => {
    const [bookings, setBookings] = useState([])
    const navigate = useNavigate()

    useEffect(() => {
        getBookingsForUser()
            .then((response) => {
                if (!Array.isArray(response.data)) {
                    throw new Error("Unexpected response from getBookingsForUser: " + response.data);
                }
                setBookings(response.data)
            })
            .catch((error) => console.error(error))
    }, [])

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