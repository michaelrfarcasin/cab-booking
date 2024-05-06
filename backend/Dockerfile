FROM maven:3.9.6-amazoncorretto-21-al2023 AS build
WORKDIR /home/cab-backend

COPY ./pom.xml /home/cab-backend/pom.xml
COPY ./src/main/java/com/booking/cab/CabBookingSystemApplication.java /home/cab-backend/src/main/java/com/booking/cab/CabBookingSystemApplication.java

RUN mvn -f /home/cab-backend/pom.xml clean package

COPY . /home/cab-backend
RUN mvn -f /home/cab-backend/pom.xml clean package

FROM openjdk:21-jdk-slim
EXPOSE 8080
COPY --from=build /home/cab-backend/target/*.jar app.jar
ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]
