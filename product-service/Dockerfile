FROM openjdk:17-jdk-slim

RUN apt-get update && apt-get install -y curl

COPY target/product-service-0.0.1-SNAPSHOT.jar product-service-0.0.1-SNAPSHOT.jar

EXPOSE 8083
EXPOSE 3306

ENTRYPOINT ["java", "-jar", "product-service-0.0.1-SNAPSHOT.jar"]