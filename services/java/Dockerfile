FROM maven:3.5.3-jdk-10-slim AS build  

COPY src /app/src  

COPY pom.xml /app  

RUN mvn -f /app/pom.xml clean package

#----------

FROM openjdk:10-jre-slim

COPY --from=build /app/target/springdemo-0.0.1-SNAPSHOT.jar /app/springdemo-0.0.1-SNAPSHOT.jar  

EXPOSE 80  

ENTRYPOINT ["java","-jar","/app/springdemo-0.0.1-SNAPSHOT.jar"]  