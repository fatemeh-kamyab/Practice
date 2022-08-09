FROM openjdk:11
COPY target/dockerPractice-1.0-SNAPSHOT.jar dockerPractice-1.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "dockerPractice-1.0-SNAPSHOT.jar"]