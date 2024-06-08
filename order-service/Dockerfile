FROM openjdk:17-jdk-slim

RUN apt-get update && apt-get install -y curl

COPY target/order-service-0.0.1-SNAPSHOT.jar order-service-0.0.1-SNAPSHOT.jar

EXPOSE 8084

ENTRYPOINT ["java", "-jar", "order-service-0.0.1-SNAPSHOT.jar"]