FROM openjdk:8-jdk-alpine
MAINTAINER "Docker App"
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
