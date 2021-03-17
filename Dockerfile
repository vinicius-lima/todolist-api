FROM openjdk:8-slim
WORKDIR /app
EXPOSE 5000
COPY ./target/todolist-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]
