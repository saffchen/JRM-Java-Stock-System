FROM openjdk:20-jdk

COPY build/libs/stock-1.0.jar /app/stock.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "stock.jar"]