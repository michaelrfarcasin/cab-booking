CREATE TABLE user_details (
	id INT NOT NULL AUTO_INCREMENT, 
	username VARCHAR(255) NOT NULL UNIQUE, 
	password VARCHAR(255) NOT NULL DEFAULT '', 
	roles VARCHAR(255) NOT NULL DEFAULT '', 
	enabled BOOLEAN NOT NULL DEFAULT 1,
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

INSERT INTO user_details (username, password, roles, enabled) VALUES ('Alice', '$2a$10$KjZzjwezAnpcGnJ1C107A.teRDoekfX4TYt6n94YrMglHGxRuygWW', 'USER', true);
INSERT INTO user_details (username, password, roles, enabled) VALUES ('Bob', '$2a$10$KjZzjwezAnpcGnJ1C107A.teRDoekfX4TYt6n94YrMglHGxRuygWW', 'USER', true);

INSERT INTO driver (name, latitude, longitude) VALUES ('Alpha', 39.7294, -104.8319);
INSERT INTO driver (name, latitude, longitude) VALUES ('Bravo', 39.7392, -104.9903);
INSERT INTO driver (name, latitude, longitude) VALUES ('Charlie', 39.5501, -105.7821);
INSERT INTO driver (name, latitude, longitude) VALUES ('Delta', 38.8339, -104.8214);
INSERT INTO driver (name, latitude, longitude) VALUES ('Echo', 39.7394, -104.8719);
INSERT INTO driver (name, latitude, longitude) VALUES ('Foxtrot', 39.7194, -104.8719);
INSERT INTO driver (name, latitude, longitude) VALUES ('Golf', 39.7394, -104.7819);
INSERT INTO driver (name, latitude, longitude) VALUES ('Hotel', 39.7194, -104.7819);

INSERT INTO booking_request(id, user_id, pick_up_latitude, pick_up_longitude, drop_off_latitude, drop_off_longitude)
    VALUES (1, 1, 39.7392, -104.9903, 39.7294, -104.8319);
    
INSERT INTO booking(id, user_id, driver_id, pick_up_latitude, pick_up_longitude, drop_off_latitude, drop_off_longitude, eta)
    VALUES (1, 1, 1, 39.7392, -104.9903, 39.7294, -104.8319, '2000-01-01 20:00:00');
INSERT INTO booking(id, user_id, driver_id, pick_up_latitude, pick_up_longitude, drop_off_latitude, drop_off_longitude, eta)
    VALUES (2, 2, 3, 39.4865, -104.4566, 39.9871, -104.1234, '2000-01-01 12:00:00');