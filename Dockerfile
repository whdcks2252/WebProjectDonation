# Start with a base image containing Java runtime
FROM openjdk:latest

# Add Author info
LABEL maintainer="chooh1010@gmail.com"

# Add a volume to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 9090

# The application's jar file
ARG JAR_FILE=build/libs/DonationWeb-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} to-do-springboot.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/to-do-springboot.jar"]