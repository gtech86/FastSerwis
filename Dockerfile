# the first stage of our build will use a maven 3.6.1 parent image

#FROM maven:3.6.1-jdk-11 AS MAVEN_BUILD
# copy the pom and src code to the container
#COPY ./ ./

# package our application code
#RUN mvn clean package -Dmaven.test.skip
#FROM openjdk:11-jdk
#RUN apt-get update
#RUN ls -al

#COPY --from=MAVEN_BUILD /target/*.jar /fast-service.jar
# set the startup command to execute the jar
#CMD ["java", "-jar", "/fast-service.jar"]

# Build stage
# Use an official Maven image as the base image
FROM maven:3.8.4-openjdk-11-slim AS build
# Set the working directory in the container
WORKDIR /app
# Copy the pom.xml and the project files to the container
COPY pom.xml .
COPY src ./src
# Build the application using Maven
RUN mvn clean package -Dmaven.test.skip
# Use an official OpenJDK image as the base image
FROM openjdk:11-jre-slim
# Set the working directory in the container

WORKDIR /app
COPY --from=build /app/target/*.jar ./fast-service.jar

# Set the command to run the application
ENTRYPOINT ["java", "-jar", "fast-service.jar"]