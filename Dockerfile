FROM java:8
FROM maven:alpine

WORKDIR /app

COPY . /app

RUN mvn -v
RUN mvn clean install -DskipTests
EXPOSE 8080
LABEL maintainer=“sinan.orhant@gmail.com”
ADD ./target/sesame-appointment-0.0.1-SNAPSHOT.jar sesame-appointment-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","sesame-appointment-0.0.1-SNAPSHOT.jar"]