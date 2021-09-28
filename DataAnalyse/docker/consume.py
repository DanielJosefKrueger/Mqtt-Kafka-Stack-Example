import json
from kafka import KafkaConsumer
if __name__ == '__main__':
    # Kafka Consumer
    consumer = KafkaConsumer(
        'kafka-test',
        bootstrap_servers='kafka:9092',
        auto_offset_reset='earliest'
    )
    for message in consumer:
        print(message.value.decode("utf-8"))