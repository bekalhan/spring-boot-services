FROM openjdk:17-jdk-slim

COPY target/cart-service-0.0.1-SNAPSHOT.jar cart-service-0.0.1-SNAPSHOT.jar

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "cart-service-0.0.1-SNAPSHOT.jar"]