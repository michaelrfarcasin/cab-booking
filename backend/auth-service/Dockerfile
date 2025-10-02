FROM maven:3.9.6-amazoncorretto-21-al2023 AS build
WORKDIR /home/auth-service

COPY ./pom.xml /home/auth-service/pom.xml
COPY ./src/main/java/auth/AuthApplication.java /home/auth-service/src/main/java/auth/AuthApplication.java

RUN mvn -f /home/auth-service/pom.xml clean package

COPY . /home/auth-service
RUN mvn -f /home/auth-service/pom.xml clean package

FROM openjdk:21-jdk-slim
EXPOSE 8080
COPY --from=build /home/auth-service/target/*.jar auth-service.jar
COPY docker-entrypoint.sh /usr/local/bin/docker-entrypoint.sh
RUN chmod +x /usr/local/bin/docker-entrypoint.sh
ENTRYPOINT ["/usr/local/bin/docker-entrypoint.sh"]
