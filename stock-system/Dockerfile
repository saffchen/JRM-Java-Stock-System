FROM maven:3.8.6-eclipse-temurin-17-alpine as builder

COPY src /app/src
COPY pom.xml /app/pom.xml

WORKDIR /app
RUN --mount=type=cache,target=/root/.m2 mvn clean package -Dmaven.test.skip

FROM eclipse-temurin:17-alpine

WORKDIR /app
COPY --from=builder /app/target/stock.jar /app/stock.jar
ENTRYPOINT ["java", "-jar", "stock.jar"]
