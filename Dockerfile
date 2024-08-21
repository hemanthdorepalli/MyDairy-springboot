# Use an official OpenJDK runtime as a parent image
FROM maven:3.8.5-openjdk-17 as bulid
COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
# Copy the built jar file from the target directory to the container
COPY --from=build /target/MyDiary-0.0.1-SNAPSHOT.jar MyDiary.jar

# Expose the port on which your Spring Boot application will run
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java","-jar","MyDiary.jar"]
