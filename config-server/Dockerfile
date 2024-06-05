FROM openjdk:17-jdk-slim

COPY target/config-server-0.0.1-SNAPSHOT.jar config-server-0.0.1-SNAPSHOT.jar

EXPOSE 8888

ENTRYPOINT ["java", "-jar", "config-server-0.0.1-SNAPSHOT.jar"]