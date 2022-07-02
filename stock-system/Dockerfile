FROM maven:3.8.5-openjdk-17-slim

COPY src /app/src
COPY pom.xml /app/pom.xml

WORKDIR /app
ARG MVN_NO_TEST
RUN mvn $MVN_NO_TEST clean package

WORKDIR /app/target
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "stock.jar"]
