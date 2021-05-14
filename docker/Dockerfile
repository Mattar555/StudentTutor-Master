FROM openjdk:8-jdk-alpine
MAINTAINER Marwan
RUN apk update && apk add netcat-openbsd
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY wait.sh /wait.sh
RUN chmod +x /wait.sh
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ENTRYPOINT ["./wait.sh"]

