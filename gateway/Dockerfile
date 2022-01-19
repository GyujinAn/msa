FROM openjdk:8-jdk-alpine
LABEL maintainer="gj.an <gj.an@okestro.com>"
RUN mkdir -p /app
ADD ./target/CloudGateway-0.0.1-SNAPSHOT.jar /app
WORKDIR /app
ENTRYPOINT ["java","-jar","CloudGateway-0.0.1-SNAPSHOT.jar","--spring.profiles.active=dev"]
