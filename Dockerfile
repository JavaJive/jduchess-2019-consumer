FROM openjdk:11-jre-slim

COPY target/consumer-application.jar /

ENTRYPOINT ["sh", "-c"]
CMD ["java -jar consumer-application.jar"]
