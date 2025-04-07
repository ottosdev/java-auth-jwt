# Etapa de build
FROM openjdk:21-jdk-slim as build

RUN apt-get update && apt-get install -y maven

WORKDIR /app
COPY . .

RUN mvn clean install -DskipTests


# Etapa final (pode usar a mesma imagem se quiser)
FROM openjdk:21-jdk-slim

WORKDIR /app
COPY --from=build /app/target/financeiro-estudo-back-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
