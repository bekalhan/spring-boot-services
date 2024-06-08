FROM openjdk:17-jdk-slim

RUN apt-get update && apt-get install -y curl

COPY target/payment-service-0.0.1-SNAPSHOT.jar payment-service-0.0.1-SNAPSHOT.jar

EXPOSE 8400

ENTRYPOINT ["java", "-jar", "payment-service-0.0.1-SNAPSHOT.jar"]