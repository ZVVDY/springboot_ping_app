FROM adoptopenjdk:17.0.9_16-jdk-hotspot AS build
WORKDIR /app
COPY . .
RUN ./mvnw package -DskipTests

FROM adoptopenjdk:17.0.9_16-jre-hotspot AS production
VOLUME /tmp
COPY --from=build /app/target/springboot_ping_app-0.0.1-SNAPSHOT.jar /app/target/ping_app.jar
ENV PORT=8081
EXPOSE $PORT
ENTRYPOINT ["java", "-jar", "ping_app.jar"]