# Setup

```
docker-compose up -d --build
```

When you're done:  

```
docker-compose down
```

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
- add frontend error messages for exceptions and error responses
- format ETA 
- make Booking form look a little nicer
- make it deployable on docker
- login page
- turn controllers into microservices
    
### MVP+1 todo
- update/cancel booking
- booking status, e.g. "en route to customer", "transporting to destination"
- driver status to prevent booking a driver that's enroute
- have drivers specific to a given region
- implement builder pattern
- create a Location data structure for Booking/Driver instead of business object
- swagger documentation
- hateoas

Guide I followed to start this project: https://www.udemy.com/course/spring-boot-and-spring-framework-tutorial-for-beginners/

## Providing the JWT secret safely

The project reads a base64-encoded HMAC secret from the `jwt.secret` property (the application references it as `jwt.secret=${JWT_SECRET:}` by default). The repo includes a tiny startup script in each image that will:

- prefer an already-set environment variable `JWT_SECRET` (this is how Elastic Beanstalk / ECS typically provide secrets),
- otherwise read a file mounted at `/run/secrets/jwt_secret` and export it as `JWT_SECRET` before starting the JVM.

This makes the image portable: it works with environment variables (cloud platforms) and with Docker secrets / mounted files for local development.

Quick examples (local development)

- Run with an environment variable:

```powershell
$env:JWT_SECRET='BASE64_ENCODED_SECRET'
mvn spring-boot:run
```

- Run via `docker-compose` using a local file (the repo uses `/run/secrets/jwt_secret` as the path inside the container):

```yaml
services:
  booking-service:
    build: ./backend/booking-service
    volumes:
      - ./secrets/jwt_secret:/run/secrets/jwt_secret:ro
    environment:
      DB_URL: jdbc:mysql://mysql-booking:3306/booking-database
      RDS_USERNAME: booking-user
      RDS_PASSWORD: dummypassword
```

If you prefer to use Docker-managed secrets (Swarm or a secrets-capable runtime) you can use the `secrets:` stanza — the entrypoint script will still read `/run/secrets/jwt_secret` in that case.

Elastic Beanstalk / ECS
- For Beanstalk or ECS it's easiest to set `JWT_SECRET` in the platform's environment variables (the entrypoint script prefers the env var). That is the recommended production pattern.

Security notes
- Do not commit the secret file. Add `secrets/jwt_secret` to your `.gitignore`.
- Prefer a secret manager (AWS Secrets Manager, Parameter Store, Vault) in production and inject secrets as environment variables or via the platform integration.

If you need an example `docker-entrypoint.sh`, the repository contains one for each service that reads `/run/secrets/jwt_secret`, strips whitespace/CRLF, and exports `JWT_SECRET` before exec-ing the JVM.