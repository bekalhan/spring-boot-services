FROM openjdk:17-jdk-slim

COPY target/payment-service-0.0.1-SNAPSHOT.jar payment-service-0.0.1-SNAPSHOT.jar

EXPOSE 8400

ENTRYPOINT ["java", "-jar", "payment-service-0.0.1-SNAPSHOT.jar"]