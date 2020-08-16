FROM java:8-jdk-alpine

WORKDIR /app

COPY /target/wellnessSpace-0.0.1-SNAPSHOT.jar ./wellnessSpace.jar
COPY /target/wellnessSpace-0.0.1-SNAPSHOT/WEB-INF/classes/META-INF/key.json /app

EXPOSE 8080

CMD ["java", "-jar", "wellnessSpace.jar"]
