
FROM amazoncorretto:17 as development

VOLUME /tmp

COPY target/springboot_ping_app.jar app.jar

ENV PORT=8081

EXPOSE $PORT

ENTRYPOINT ["java", "-jar", "app.jar"]