
FROM amazoncorretto:17 as development

VOLUME /tmp

COPY target/springboot_ping_app-0.0.1-SNAPSHOT.jar app.jar

ENV PORT=8081

EXPOSE $PORT

ENTRYPOINT ["java", "-jar", "app.jar"]
