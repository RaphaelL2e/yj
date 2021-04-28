FROM openjdk:8-jdk-alpine
COPY target/maven-archiver maven-archiver
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]