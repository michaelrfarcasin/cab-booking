-- Insert drivers with explicit IDs (entities don't use @GeneratedValue)
INSERT INTO driver (id, name, latitude, longitude) VALUES (1, 'Alpha', 39.7294, -104.8319);
INSERT INTO driver (id, name, latitude, longitude) VALUES (2, 'Charlie', 39.5501, -105.7821);

-- Insert booking_request with explicit id
INSERT INTO booking_request(id, user_id, pick_up_latitude, pick_up_longitude, drop_off_latitude, drop_off_longitude)
    VALUES (1, 1, 39.7392, -104.9903, 39.7294, -104.8319);
    
-- Insert bookings with explicit ids and matching driver ids
INSERT INTO booking(id, user_id, driver_id, pick_up_latitude, pick_up_longitude, drop_off_latitude, drop_off_longitude, eta)
    VALUES (1, 1, 1, 39.7392, -104.9903, 39.7294, -104.8319, '2000-01-01 20:00:00');
INSERT INTO booking(id, user_id, driver_id, pick_up_latitude, pick_up_longitude, drop_off_latitude, drop_off_longitude, eta)
    VALUES (2, 2, 2, 39.4865, -104.4566, 39.9871, -104.1234, '2000-01-01 12:00:00');
