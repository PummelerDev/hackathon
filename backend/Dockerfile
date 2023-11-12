FROM maven:3.6.0-jdk-11-slim AS builder
WORKDIR /app
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package -DskipTests=true

FROM adoptopenjdk/openjdk11
RUN apt-get update && apt-get install -y iputils-ping
RUN apt-get update && apt-get install -y telnet tcpdump iputils-ping dnsutils
WORKDIR /app
COPY --from=builder /app/target/backend-0.0.1-SNAPSHOT.jar /usr/local/lib/backend.jar
EXPOSE 8080 8080
ENTRYPOINT  ["java","-Xmx128m","-jar","/usr/local/lib/backend.jar"]