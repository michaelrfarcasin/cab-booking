FROM maven:3.9.6-amazoncorretto-21-al2023 AS build
WORKDIR /home/booking-service

COPY ./pom.xml /home/booking-service/pom.xml
COPY ./src/main/java/com/booking/cab/CabBookingSystemApplication.java /home/booking-service/src/main/java/com/booking/cab/CabBookingSystemApplication.java

RUN mvn -f /home/booking-service/pom.xml clean package

COPY . /home/booking-service
RUN mvn -f /home/booking-service/pom.xml clean package

FROM openjdk:21-jdk-slim
EXPOSE 8080
COPY --from=build /home/booking-service/target/*.jar booking-service.jar
ENTRYPOINT [ "sh", "-c", "java -jar /booking-service.jar" ]
