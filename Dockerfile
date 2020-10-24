FROM openjdk:14-alpine
COPY target/grpc-cat-api-*.jar grpc-cat-api.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "grpc-cat-api.jar"]