/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    `java-library`
    `maven-publish`
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:2.6.4")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.6.4")
    implementation("org.springframework.boot:spring-boot-starter-security:2.6.4")
    implementation("org.springframework.boot:spring-boot-starter-validation:2.6.4")
    implementation("org.springframework.boot:spring-boot-starter-actuator:2.6.4")

    implementation("org.slf4j:slf4j-api:1.7.36")
    implementation("org.springdoc:springdoc-openapi-ui:1.6.9")
    runtimeOnly("ch.qos.logback:logback-classic:1.2.11")
    implementation("org.glassfish:jakarta.el:4.0.1")
    implementation("org.mindrot:jbcrypt:0.4")
    implementation("org.postgresql:postgresql:42.3.8")
    implementation("org.springframework.kafka:spring-kafka:2.8.3")
    implementation("com.auth0:java-jwt:4.0.0")
    implementation("org.mapstruct:mapstruct:1.5.3.Final")
    implementation("com.google.apis:google-api-services-sheets:v4-rev493-1.23.0")
    implementation("org.apache.poi:poi-ooxml:3.12")
    implementation("com.opencsv:opencsv:5.6")
    implementation("com.google.api-client:google-api-client:1.23.0")
    implementation("com.google.oauth-client:google-oauth-client-jetty:1.23.0")
    implementation("com.itextpdf:itextpdf:5.5.13.3")
    implementation("com.google.apis:google-api-services-sheets:v4-rev493-1.23.0")
    implementation("org.apache.poi:poi-ooxml:3.12")
    implementation("com.itextpdf:itextpdf:5.5.13.3")
    implementation("com.opencsv:opencsv:5.6")
    implementation("com.google.oauth-client:google-oauth-client-jetty:1.23.0")
    implementation("org.projectlombok:lombok:1.18.22")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-hibernate5")
    annotationProcessor("org.projectlombok:lombok:1.18.22")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.3.Final")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.7.5")
    testImplementation("org.springframework.security:spring-security-test:5.7.5")
}

group = "ru.javaroadmap"
version = "1.0"
java.sourceCompatibility = JavaVersion.VERSION_17

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}
