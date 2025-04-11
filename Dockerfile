# Build stage
# Start with maven:3.8.7-amazoncorretto-21 base image
FROM maven:3.9.9-amazoncorretto-21 AS build

# copy pom.xml from context into image
COPY pom.xml /app/
COPY src /app/src
RUN ["mvn", "-f", "/app/pom.xml", "clean", "package", "-Dmaven.test.skip=true"]


#Run stage
FROM amazoncorretto:21

ARG JAR_NAME="patient-api-0.0.1-SNAPSHOT.jar"

# Copia o JAR gerado pelo Gradle/Maven para dentro do container
COPY --from=build /app/target/${JAR_NAME} /app/${JAR_NAME}

# Expõe a porta do Spring Boot
EXPOSE "8080:8080"

# Comando para rodar a aplicação
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/patient-api-0.0.1-SNAPSHOT.jar"]