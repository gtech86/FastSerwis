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
FROM maven:3.6.1-jdk-11 AS MAVEN_BUILD
WORKDIR /build
COPY pom.xml /build/
COPY src /build/src/
RUN mvn clean package -Dmaven.test.skip
RUN ls -al target

# Runtime stage
FROM openjdk:11-jdk
RUN apt-get update -y
WORKDIR /
COPY --from=MAVEN_BUILD /build/target/*.jar /fast-service.jar
CMD ["java", "-jar", "/fast-service.jar"]
