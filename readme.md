# Setup

$ docker-compose build
$ docker-compose up -d

When you're done:  
$ docker-compose down

# Deployment

Go to http://localhost:3000/

To see some test data, login with the username Alice, password dummy

# Overview

https://www.geeksforgeeks.org/java-projects/

### 30. Online Cab Booking System 
Ola and Uber are the online cab booking systems that almost every one of us has used. 
So building such a Java application would be the best idea. 

Abstract:
In this project, the main objective is to help customers in booking a cab to reach their destination 
with pick-up as their preferred location. The application fetches your pick-up location and asks you 
to enter the drop location, when entered, finds a cab driver nearby and even tells you the calculated 
time the cab will take to drop you at the location. 

Technologies: Java, HTML, CSS, JavaScript, JSP, JDBC, JWT, Java Spring, Java Servlet, MySQL, and 
Tomcat Server.

### Development
To create a local mysql server for testing, use this command: 

```
docker run --detach --env MYSQL_ROOT_PASSWORD=dummypassword --env MYSQL_USER=booking-user --env MYSQL_PASSWORD=dummypassword --env MYSQL_DATABASE=booking-database --name mysql --publish 3306:3306 mysql:8-oracle
```

### MVP Breakdown:
- book a cab
	- pickup location
	- destination location
	- confirm booking
- find nearby cab
- calculate ETA
	- time to reach user + time to reach destination
- display booking with ETA to user

### MVP+1 done
- format ETA 
- make Booking form look a little nicer
- make it deployable on docker
- login page
	
### MVP+1 todo
- update/cancel booking
- booking status, e.g. "en route to customer", "transporting to destination"
- driver status to prevent booking a driver that's enroute
- have drivers specific to a given region
- implement builder pattern
- create a Location data structure for Booking/Driver instead of business object
- make each controller a microservice
- swagger documentation
- hateoas

Guide used in building this project: https://www.udemy.com/course/spring-boot-and-spring-framework-tutorial-for-beginners/