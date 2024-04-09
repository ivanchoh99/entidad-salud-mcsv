ARG PATH_JAR=/mcsv-usuarios/build/libs
ARG PORT_APP=8002

FROM gradle:jdk17-alpine as builder

WORKDIR /mcsv-usuarios

COPY  ./build.gradle .
COPY ./src ./src

RUN gradle clean --build-cache bootJar

FROM openjdk:17.0.2-jdk-slim

ARG PATH_JAR
ARG PORT_APP

WORKDIR /app

COPY --from=builder $PATH_JAR/mcsv-usuarios-0.0.1-SNAPSHOT.jar .

EXPOSE $PORT_APP

ENTRYPOINT ["java", "-jar", "mcsv-usuarios-0.0.1-SNAPSHOT.jar"]


LABEL authors="ivanh"
