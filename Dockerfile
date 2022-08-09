FROM openjdk:11
COPY target/dockerPractice-1.0-SNAPSHOT.jar docker-welcome-1.0.jar
ENTRYPOINT ["java", "-jar", "docker-welcome-1.0.jar"]