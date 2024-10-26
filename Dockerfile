FROM openjdk:17.0.1-jdk-slim
WORKDIR /lms
COPY target/*.jar lms.jar
ENTRYPOINT ["java", "-jar", "lms.jar"]