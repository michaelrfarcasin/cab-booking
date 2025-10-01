CREATE TABLE user_details (
	id INT NOT NULL AUTO_INCREMENT, 
	username VARCHAR(255) NOT NULL UNIQUE, 
	password VARCHAR(255) NOT NULL DEFAULT '', 
	roles VARCHAR(255) NOT NULL DEFAULT '', 
	enabled BOOLEAN NOT NULL DEFAULT 1,
	PRIMARY KEY (id)
);

INSERT INTO user_details (username, password, roles, enabled) VALUES ('Alice', '$2a$10$KjZzjwezAnpcGnJ1C107A.teRDoekfX4TYt6n94YrMglHGxRuygWW', 'USER', true);
INSERT INTO user_details (username, password, roles, enabled) VALUES ('Bob', '$2a$10$KjZzjwezAnpcGnJ1C107A.teRDoekfX4TYt6n94YrMglHGxRuygWW', 'USER', true);