FROM gradle:8.5-jdk17 AS build
WORKDIR /app
COPY . /app
RUN gradle clean build

FROM openjdk:17-jdk
COPY --from=build /app/build/libs/*.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]