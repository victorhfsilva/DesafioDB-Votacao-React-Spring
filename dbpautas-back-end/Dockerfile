FROM gradle:8.4.0-jdk17 AS build

WORKDIR /dbpautas-back-end

COPY build.gradle .
COPY settings.gradle .
COPY src /dbpautas-back-end/src

RUN gradle clean build -x test

FROM eclipse-temurin:17-jdk-alpine

COPY --from=build /dbpautas-back-end/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]