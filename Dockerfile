FROM maven:alpine AS dependencies
WORKDIR /pet-doctor/employee-service
COPY pom.xml ./
RUN mvn dependency:resolve

FROM maven:alpine AS build
COPY --from=dependencies /pet-doctor/employee-service /pet-doctor/employee-service
WORKDIR /pet-doctor/employee-service
COPY ./src ./src
RUN mvn package

FROM openjdk:19
COPY --from=build /pet-doctor/employee-service/target/EmployeeService-0.0.1-SNAPSHOT-exec.jar ./employee-service.jar
ENTRYPOINT ["java", "-jar", "employee-service.jar"]


