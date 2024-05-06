CREATE TABLE user_details (
	id INT NOT NULL AUTO_INCREMENT, 
	name VARCHAR(255) NOT NULL DEFAULT '', 
	PRIMARY KEY (id)
);

CREATE TABLE driver (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL DEFAULT '',
    latitude DECIMAL(8,6) NOT NULL DEFAULT 0.0,
    longitude DECIMAL(9,6) NOT NULL DEFAULT 0.0, 
    PRIMARY KEY (id)
);

CREATE TABLE booking_request (
    id INT NOT NULL AUTO_INCREMENT, 
    user_id INT NOT NULL,
    pick_up_latitude DECIMAL(8,6) NOT NULL DEFAULT 0.0,
    pick_up_longitude DECIMAL(9,6) NOT NULL DEFAULT 0.0,
    drop_off_latitude DECIMAL(8,6) NOT NULL DEFAULT 0.0,
    drop_off_longitude DECIMAL(9,6) NOT NULL DEFAULT 0.0,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user_details(id) ON DELETE CASCADE
);

CREATE TABLE booking (
    id INT NOT NULL AUTO_INCREMENT, 
    user_id INT NOT NULL,
    driver_id INT NOT NULL,
    pick_up_latitude DECIMAL(8,6) NOT NULL DEFAULT 0.0,
    pick_up_longitude DECIMAL(9,6) NOT NULL DEFAULT 0.0,
    drop_off_latitude DECIMAL(8,6) NOT NULL DEFAULT 0.0,
    drop_off_longitude DECIMAL(9,6) NOT NULL DEFAULT 0.0,
    eta DATETIME NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user_details(id) ON DELETE CASCADE,
    FOREIGN KEY (driver_id) REFERENCES driver(id) ON DELETE CASCADE
);

