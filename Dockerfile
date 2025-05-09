FROM gradle:8-jdk17-alpine AS build

WORKDIR /app

COPY . .

RUN gradle build

FROM openjdk:17-alpine

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

#COPY build/libs/vebRaiz-test-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]