FROM openjdk:17-jdk-slim

RUN apt-get update && apt-get install -y curl

COPY target/auth-service-0.0.1-SNAPSHOT.jar auth-service-0.0.1-SNAPSHOT.jar

EXPOSE 8081
EXPOSE 3306

ENTRYPOINT ["java", "-jar", "auth-service-0.0.1-SNAPSHOT.jar"]