FROM mysql:8-oracle
WORKDIR /home/cab-mysql

ENV MYSQL_ROOT_PASSWORD=dummypassword \
	MYSQL_USER=booking-user \
	MYSQL_PASSWORD=dummypassword \
	MYSQL_DATABASE=booking-database

COPY ./sql-scripts /docker-entrypoint-initdb.d

EXPOSE 3306