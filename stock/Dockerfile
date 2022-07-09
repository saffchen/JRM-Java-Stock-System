FROM eclipse-temurin:17-alpine

COPY target/stock.jar /app/stock.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "stock.jar"]
