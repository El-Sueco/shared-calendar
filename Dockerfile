FROM amazoncorretto:21
EXPOSE 8080
ARG JAR_FILE=backend/target/backend-0.1.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]