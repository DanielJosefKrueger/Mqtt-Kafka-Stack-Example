FROM hivemq/hivemq4:4.6.4
RUN rm /opt/hivemq/extensions/hivemq-kafka-extension/DISABLED
RUN rm /opt/hivemq/extensions/hivemq-kafka-extension/kafka-configuration.xml
COPY ./kafka-extension/kafka-configuration.xml /opt/hivemq/extensions/hivemq-kafka-extension/kafka-configuration.xml
