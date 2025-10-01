import LocationComponent from './LocationComponent'

const ListBookingsComponent = ({bookings}) => {
    bookings = bookings.map(booking => {
        let dateTime = new Date(booking.eta)
        booking.eta = dateTime.toLocaleString('en-US', { timeZone: 'MST' })
        return booking
    })

    return (
        <div className="Bookings">
            <table className="table">
                <thead>
                        <tr>
                            <th>Driver</th>
                            <th>Driver location</th>
                            <th>Pick up location</th>
                            <th>Drop off location</th>
                            <th>Estimated Time of Arrival</th>
                        </tr>
                </thead>
                <tbody>
                {
                    bookings.map(
                        booking => (
                            <tr key={booking.id}>
                                <td>{booking.driver.name}</td>
                                <td><LocationComponent location={booking.driver.location} /></td>
                                <td><LocationComponent location={booking.pickUp} /></td>
                                <td><LocationComponent location={booking.dropOff} /></td>
                                <td>{booking.eta}</td>
                            </tr>
                        )
                    )
                }
                </tbody>

            </table>
        </div>
    )
}

export default ListBookingsComponent;