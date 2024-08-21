# Use an official OpenJDK runtime as a parent image
FROM openjdk:22-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the built jar file from the target directory to the container
COPY target/mydiary-0.0.1-SNAPSHOT.jar app.jar

# Expose the port on which your Spring Boot application will run
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java","-jar","app.jar"]
