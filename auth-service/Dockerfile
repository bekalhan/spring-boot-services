FROM openjdk:17-jdk-slim

COPY target/auth-service-0.0.1-SNAPSHOT.jar auth-service-0.0.1-SNAPSHOT.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "auth-service-0.0.1-SNAPSHOT.jar"]