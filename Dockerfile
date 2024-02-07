# Use an official OpenJDK runtime as a parent image
FROM openjdk:8-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container at /app
COPY target/ecomApp-0.0.1-SNAPSHOT.jar /app/ecomApp-0.0.1-SNAPSHOT.jar

# Expose the port the app runs on
EXPOSE 8080

# Specify the command to run on container start
CMD ["java", "-jar", "ecomApp-0.0.1-SNAPSHOT.jar"]