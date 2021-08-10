# We are using the Java 11 version of GraalVM CE 21.0.0
FROM ghcr.io/graalvm/graalvm-ce:java11-21.0.0 as graalvm

# Install the native image tool
RUN gu install native-image

# Set the build directory
WORKDIR /home/build

# Install UPX
RUN microdnf update -y oraclelinux-release-el8 && microdnf --enablerepo ol8_codeready_builder install xz && microdnf clean all && \
    curl --location --output upx-3.96-amd64_linux.tar.xz "https://github.com/upx/upx/releases/download/v3.96/upx-3.96-amd64_linux.tar.xz" && \
    tar -xJf upx-3.96-amd64_linux.tar.xz && \
    cp upx-3.96-amd64_linux/upx /bin/

# Copy the shaded application jar file to the build directory
COPY target/grpc-cat-api-*.jar /home/build/

# Create the native image executable
RUN native-image -jar grpc-cat-api-*.jar && mv grpc-cat-api api

# Compress executable with UPX
RUN upx --best --lzma api

# Create Docker image from the Distroless base image
FROM gcr.io/distroless/base-debian10
EXPOSE 50051
COPY --from=graalvm /home/build/api /app/api
WORKDIR /app/
ENTRYPOINT ["/app/api"]
