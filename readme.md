# Setup

$ docker-compose build  
$ docker-compose up -d

When you're done:  
$ docker-compose down

# Deployment

Go to http://localhost:3000/

To see some test data, login with the username Alice (no password)

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
The system is designed using Spring MVC, Servlets, Hibernate, JDBC, JSP, HTML, and CSS. 

Technologies: Java, HTML, CSS, JavaScript, JSP, JDBC, Java Spring, Java Servlet, MySQL, and Tomcat Server.
I used JPA instead of JDBC and React instead of JSP for my own practice.

### Breakdown:
- book a cab
	- pickup location
	- destination location
	- confirm booking
- find nearby cab
- calculate ETA
	- time to reach user + time to reach destination

### MVP+1 done
- format ETA 
- make Booking form look a little nicer
- make it deployable on docker
	
### MVP+1 todo
- login page
	- Guides:
	- https://www.udemy.com/course/spring-boot-and-spring-framework-tutorial-for-beginners/learn/lecture/35017828
	- video: 252. Step 11 (Section 12)
	- Section 7 has: jsp, spring security
- update/cancel booking
- booking status, e.g. "en route to customer", "transporting to destination"
- driver status to prevent booking a driver that's enroute
- have drivers specific to a given region
- implement builder pattern
- create a Location data structure for Booking/Driver instead of business object
- make each controller a microservice
- swagger documentation
- hateoas
