FROM maven:3.5.3-jdk-10-slim AS build  

WORKDIR /app

COPY src src/  

COPY pom.xml .

RUN mvn -f pom.xml clean package

#----------

FROM openjdk:10-jre-slim

COPY --from=build /app/target/springdemo-0.0.1-SNAPSHOT.jar /app/springdemo-0.0.1-SNAPSHOT.jar  

EXPOSE 80  

ENTRYPOINT ["java","-jar","/app/springdemo-0.0.1-SNAPSHOT.jar"]  