FROM gradle:4.7.0-jdk8-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon 

FROM openjdk:8-jre-slim AS run

EXPOSE 8080

RUN mkdir /app

COPY --from=build build/libs/*.jar /app/budget-planner-a.jar

ENTRYPOINT ["java","-jar","/app/budget-planner-a.jar"]