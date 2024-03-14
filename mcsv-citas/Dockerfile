FROM gradle:jdk17-alpine as builder

WORKDIR /mcsv-citas

COPY  ./build.gradle .
COPY ./src ./src

RUN gradle clean build -x test


FROM openjdk:17.0.2-jdk-slim

WORKDIR /app

COPY --from=builder /mcsv-citas/build/libs/mcsv-citas-0.0.1-SNAPSHOT.jar .

EXPOSE 8001

ENTRYPOINT ["java", "-jar", "mcsv-citas-0.0.1-SNAPSHOT.jar"]


LABEL authors="ivanh"
