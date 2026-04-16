# Use an official OpenJDK 21 image as a base
FROM eclipse-temurin:21-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the Maven wrapper and pom.xml files
COPY mvnw mvnw
COPY mvnw.cmd mvnw.cmd
COPY .mvn .mvn
COPY pom.xml pom.xml

# Copy the source code
COPY src src

# Make the Maven wrapper executable
RUN chmod +x mvnw

# Build the application (skip tests for faster build, can be changed)
RUN ./mvnw clean package -DskipTests

# Use a minimal runtime image for running the app
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy the built jar from the build stage
COPY --from=0 /app/target/flight-api-0.0.1-SNAPSHOT.jar app.jar

# Expose the default Spring Boot port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
