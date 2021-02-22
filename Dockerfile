# We are using the Java 11 version of GraalVM CE 21.0.0
FROM ghcr.io/graalvm/graalvm-ce:java11-21.0.0 as graalvm

# Install the native image tool
RUN gu install native-image

# Set the build directory
WORKDIR /home/build

# Copy the shaded application jar file to the build directory
COPY target/grpc-cat-api-*.jar /home/build/

# Create the native image executable
RUN native-image -jar grpc-cat-api-*.jar && mv grpc-cat-api api

# Create Docker image from the Distroless base image
FROM gcr.io/distroless/base-debian10
EXPOSE 50051
COPY --from=graalvm /home/build/api /app/api
WORKDIR /app/
ENTRYPOINT ["/app/api"]
