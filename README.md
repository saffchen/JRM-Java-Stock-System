# JRM Java Stock System Project

## Run application (development approach):
<img alt="docker" src="https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white"/>

### 1. Clone project repository.
### 2. Make sure you have docker installed.
#### 2a. On Windows OS make sure that docker engine has started.
### 3. From the root directory of the project with root privileges run:
a.
```bash
$ docker compose up --build -d postgres
```
b.
```bash
$ ./mvnw clean install #linux/macOS
$ mvnw.cmd clean install #windows 
```
c.
```bash
$ docker compose up --build -d
```

### 4. After that you can open <i>http://localhost:3000</i> in your browser.
#### 4a. Accessing SwaggerUI by following <i>http://localhost:8080/swagger-ui/index.html </i>

## Stop application:

### To stop application and remove the containers run:
```bash
$ docker compose down
```
