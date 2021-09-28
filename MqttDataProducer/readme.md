# Mqtt - Kafka - Fullstack Demo

DISCLAIMER: I`m a dev at HiveMQ, but this is not associated with my work there. This is a private work that was created at my free time.

## Idea

I want to create a easy setup that creates Data, publishes them via MQTT to a HiveMQ Broker and via the Kafka Extension to Kafka.
From Kafka it is consumed by a Python Script, some basic data science is done and the data is put back at a different Kafka topic. 
From here it will be consumed by the HiveMQ broker again and sent out to the subscribers.

I want it to be as easy as possible and therefore create a docker-compose script 
and later a script for K8s. 


MQTT-Producer - TODO
* Replace sout´s with logger