FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY /target/ecommerce-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java","-jar","-noverify","ecommerce-0.0.1-SNAPSHOT.jar"]