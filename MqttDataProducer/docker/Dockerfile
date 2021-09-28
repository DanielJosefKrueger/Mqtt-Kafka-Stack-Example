FROM openjdk:16-alpine3.13
COPY ./MqttDataProducer-1.0-SNAPSHOT-all.jar /opt/producer.jar
ENTRYPOINT ["java", "-jar", "/opt/producer.jar"]