FROM openjdk:20-jdk

COPY target/stock.jar /app/stock.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "stock.jar"]