ARG PATH_JAR=/mcsv-citas/build/libs
ARG PORT_APP=8001

FROM gradle:jdk17-alpine as builder

WORKDIR /mcsv-citas

COPY  ./build.gradle .
COPY ./src ./src

RUN gradle clean --build-cache bootJar


FROM openjdk:17.0.2-jdk-slim

ARG PATH_JAR
ARG PORT_APP

WORKDIR /app

COPY --from=builder $PATH_JAR/mcsv-citas-0.0.1-SNAPSHOT.jar .
ENV PORT $PORT_APP

EXPOSE $PORT

ENTRYPOINT ["java", "-jar", "mcsv-citas-0.0.1-SNAPSHOT.jar"]


LABEL authors="ivanh"
