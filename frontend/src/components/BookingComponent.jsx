import './BookingComponent.css'
import { useNavigate } from 'react-router-dom'
import { addBookingForUser } from './api/BookingApiService'
import { Formik, Form, Field, ErrorMessage } from 'formik'
import { useState } from 'react'

const BookingComponent = () => {
    const [pickUpLatitude] = useState('')
    const [pickUpLongitude] = useState('')
    const [dropOffLatitude] = useState('')
    const [dropOffLongitude] = useState('')
    
    const navigate = useNavigate()

    const onSubmit = (values) => {
        const booking = {
            id: 0,
            pickUpLatitude: values.pickUpLatitude, 
            pickUpLongitude: values.pickUpLongitude, 
            dropOffLatitude: values.dropOffLatitude, 
            dropOffLongitude: values.dropOffLongitude
        }

        addBookingForUser(booking)
            .then(response => navigate('/'))
            .catch(error => console.error(error))
    }

    const validate = (values) => {
        let errors = {}
        
        let latitude = parseFloat(values.pickUpLatitude)
        if(latitude < -90 || latitude > 90) {
            errors.pickUpLatitude = 'Latitude must be between -90 and 90'
        }
        latitude = parseFloat(values.dropOffLatitude)
        if(latitude < -90 || latitude > 90) {
            errors.dropOffLatitude = 'Latitude must be between -90 and 90'
        }
        let longitude = parseFloat(values.pickUpLongitude)
        if(longitude < -180 || longitude > 180) {
            errors.pickUpLongitude = 'Longitude must be between -180 and 180'
        }
        longitude = parseFloat(values.dropOffLongitude)
        if(longitude < -180 || longitude > 180) {
            errors.dropOffLongitude = 'Longitude must be between -180 and 180'
        }

        return errors;
    }

    return (
        <div className="container">
            <div>
                <Formik initialValues={{ pickUpLatitude, pickUpLongitude, dropOffLatitude, dropOffLongitude }}
                    enableReinitialize = {true}
                    onSubmit = {onSubmit}
                    validate = {validate}
                    validateOnChange = {false}
                    validateOnBlur = {false}
                >
                {(props) => (
                    <Form>
                        <fieldset className="form-group">
                            <h2>Pick up location</h2>
                            <div className="locationInputWrapper">
                                <ErrorMessage 
                                    name="pickUpLatitude"
                                    component="div"
                                    className = "alert alert-warning"
                                />
                                <ErrorMessage 
                                    name="pickUpLongitude"
                                    component="div"
                                    className = "alert alert-warning"
                                />
                                <label>Latitude</label>
                                <Field type="text" className="form-control" name="pickUpLatitude" />
                                <label>Longitude</label>
                                <Field type="text" className="form-control" name="pickUpLongitude" />
                            </div>
                        </fieldset>
                        <fieldset className="form-group">
                            <h2>Drop off location</h2>
                            <div className="locationInputWrapper">
                                <ErrorMessage 
                                    name="dropOffLatitude"
                                    component="div"
                                    className = "alert alert-warning"
                                />
                                <ErrorMessage 
                                    name="dropOffLongitude"
                                    component="div"
                                    className = "alert alert-warning"
                                />
                                <label>Latitude</label>
                                <Field type="text" className="form-control" name="dropOffLatitude"/>
                                <label>Longitude</label>
                                <Field type="text" className="form-control" name="dropOffLongitude"/>
                            </div>
                        </fieldset>
                        <div>
                            <button className="btn btn-success m-5" type="submit">Book ride</button>
                        </div>
                    </Form>
                )}
                </Formik>
            </div>
        </div>
    )
}

export default BookingComponent;