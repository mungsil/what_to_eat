FROM docker.io/library/eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY /app/build/libs/*.jar app.jar

EXPOSE 8080 9090
ENTRYPOINT ["java", "-jar", "app.jar"]