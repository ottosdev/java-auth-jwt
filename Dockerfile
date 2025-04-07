# Etapa de build
FROM ubuntu:latest as build

RUN apt-get update
RUN apt-get install -y openjdk-17-jdk maven

WORKDIR /app
COPY . .

RUN mvn clean install

# Etapa final
FROM openjdk:21-jdk-slim

EXPOSE 8080

COPY --from=build /app/target/financeiro-estudo-back-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
