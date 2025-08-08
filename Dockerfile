FROM ubuntu:22.04

# Java 설치 (OpenJDK 17 예시)
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk && \
    apt-get clean

WORKDIR /app

COPY build/libs/demo-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]
