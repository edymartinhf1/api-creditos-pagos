FROM openjdk:11.0.16
WORKDIR /app
COPY ./target/api-creditos-pagos-0.0.1-SNAPSHOT.jar .
EXPOSE 8081
ENTRYPOINT ["java","-jar","api-creditos-pagos-0.0.1-SNAPSHOT.jar"]


