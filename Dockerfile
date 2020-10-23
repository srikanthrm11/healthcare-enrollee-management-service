FROM openjdk:8-jdk-alpine
MAINTAINER "Docker App"
COPY target/*.jar app.jar
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongo:27017/Enrollees","-jar", "/app.jar"]
