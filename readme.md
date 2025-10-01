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

The project reads a base64-encoded HMAC secret from the `jwt.secret` property as a demonstration. This wouldn't be appropriate for production code. Instead prefer one of the options below and reference the value in `application.properties` with property substitution, for example:

```
jwt.secret=${JWT_SECRET:}
```

Recommended ways to supply the secret at runtime:

- Environment variable (local development or Docker):

    PowerShell:
    ```powershell
    $env:JWT_SECRET = 'BASE64_ENCODED_SECRET'
    mvn spring-boot:run
    ```

    Or when running the JAR:
    ```powershell
    $env:JWT_SECRET='BASE64_ENCODED_SECRET'; java -jar target/cab-0.0.1-SNAPSHOT.jar
    ```

- Docker secret (recommended for Docker Compose / production):

    1. Create a file containing the secret (outside source tree):
         ```powershell
         Set-Content -Path C:\secrets\jwt_secret.txt -Value 'BASE64_ENCODED_SECRET'
         ```
    2. Reference it in `docker-compose.yml` as a secret and mount into the container, or set an ENV var from the file in your container entrypoint.

    Example `docker-compose.yml` snippet (compose v3.4+):

    ```yaml
    version: '3.8'
    services:
      booking-service:
        build: ./backend/booking-service
        secrets:
          - jwt_secret
        environment:
          # the entrypoint script will export JWT_SECRET from the mounted secret file
          - JWT_SECRET_FILE=/run/secrets/jwt_secret

    secrets:
      jwt_secret:
        file: ./secrets/jwt_secret.txt  # point to an external file (keep out of repo)
    ```

    Minimal `Dockerfile` adjustments to expose the secret as an env var to the JVM (example):

    ```dockerfile
    FROM eclipse-temurin:21-jre

    WORKDIR /app
    COPY target/cab-0.0.1-SNAPSHOT.jar app.jar

    # small entrypoint that exports the secret from the Docker secret file into an env var
    COPY docker-entrypoint.sh /usr/local/bin/docker-entrypoint.sh
    RUN chmod +x /usr/local/bin/docker-entrypoint.sh

    ENTRYPOINT ["/usr/local/bin/docker-entrypoint.sh"]
    CMD ["java","-jar","/app/app.jar"]
    ```

    `docker-entrypoint.sh` (place next to Dockerfile, not checked into secrets):

    ```sh
    #!/bin/sh
    # If the secret file is provided, export it as JWT_SECRET for the JVM to pick up
    if [ -n "$JWT_SECRET_FILE" ] && [ -f "$JWT_SECRET_FILE" ]; then
      export JWT_SECRET=$(cat "$JWT_SECRET_FILE")
    fi

    exec "$@"
    ```

- Kubernetes secret (recommended for k8s deployments):

    Create a secret and mount as env var:
    ```bash
    kubectl create secret generic jwt-secret --from-literal=JWT_SECRET=BASE64_ENCODED_SECRET
    # then reference in your Deployment spec as an env var from the secret
    ```

If you prefer not to change the properties file, you can also pass the property on the command line:

```powershell
mvn -Djwt.secret=BASE64_ENCODED_SECRET spring-boot:run
```

Security tips:
- Rotate secrets regularly.
- Use a secret manager (Azure Key Vault, AWS Secrets Manager, HashiCorp Vault) in production.
- Keep the secret outside the repository and limit who can read it.