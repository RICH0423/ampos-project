FROM openjdk:8-jdk-alpine
MAINTAINER Rich.Lee <rich04230@gmail.com>

VOLUME /tmp
EXPOSE 8081

COPY target/order-management-1.0.0.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java -jar /app.jar
