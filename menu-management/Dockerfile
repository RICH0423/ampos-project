FROM openjdk:8-jdk-alpine
MAINTAINER Rich.Lee <rich04230@gmail.com>

VOLUME /tmp
EXPOSE 8080

COPY target/menu-management-1.0.0.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java -jar /app.jar