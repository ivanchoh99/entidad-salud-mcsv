FROM gradle:jdk17-alpine as builder

WORKDIR /mcsv-usuario

COPY  ./build.gradle .
COPY ./src ./src

RUN gradle clean build -x test


FROM openjdk:17.0.2-oraclelinux8

WORKDIR /app

COPY --from=builder /mcsv-usuario/build/libs/mcsv-usuario-0.0.1-SNAPSHOT.jar .

EXPOSE 8002

ENTRYPOINT ["java", "-jar", "mcsv-usuario-0.0.1-SNAPSHOT.jar"]


LABEL authors="ivanh"
