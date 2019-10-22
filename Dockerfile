FROM openjdk:11-jre-slim

COPY target/consumer-application.jar /
COPY src/main/resources/application.yaml /

ENTRYPOINT ["sh", "-c"]
CMD ["java -jar consumer-application.jar"]
