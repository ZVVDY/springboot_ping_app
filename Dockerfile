FROM openjdk:17 AS build
WORKDIR /app
COPY . .
RUN ./mvnw package -DskipTests

FROM openjdk:17 AS production
VOLUME /tmp
COPY --from=build /app/target/springboot_ping_app-0.0.1-SNAPSHOT.jar ping_app.jar
ENV PORT=8081
EXPOSE $PORT
ENTRYPOINT ["java", "-jar", "ping_app.jar"]