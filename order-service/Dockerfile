FROM openjdk:17-jdk-slim

COPY target/order-service-0.0.1-SNAPSHOT.jar order-service-0.0.1-SNAPSHOT.jar

EXPOSE 8084

ENTRYPOINT ["java", "-jar", "order-service-0.0.1-SNAPSHOT.jar"]