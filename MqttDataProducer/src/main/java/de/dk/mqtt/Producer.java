package de.dk.mqtt;

import com.hivemq.client.mqtt.datatypes.MqttQos;
import com.hivemq.client.mqtt.mqtt5.Mqtt5BlockingClient;
import com.hivemq.client.mqtt.mqtt5.Mqtt5Client;
import com.hivemq.client.mqtt.mqtt5.message.connect.connack.Mqtt5ConnAck;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer {

    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    private final AtomicInteger counter = new AtomicInteger();
    private final Mqtt5BlockingClient mqtt5BlockingClient;

    private Producer(){
        String host = EnvVarUtil.getOrDefault("BROKER_HOST", "localhost");
        int port = EnvVarUtil.getOrDefault("BROKER_PORT", 1883);
        mqtt5BlockingClient = Mqtt5Client.builder()
                .serverHost(host)
                .serverPort(port)
                .automaticReconnectWithDefaultConfig()
                .buildBlocking();
    }

    private void startPublishing(){
        final Random random = new Random();

        Mqtt5ConnAck connAck = mqtt5BlockingClient.connect();
        if(connAck.getReasonCode().isError()){
            System.out.println("ConnAck reason code contained error, reason code: " + connAck.getReasonCode() + "reason string: " + connAck.getReasonString());
        }else{
            System.out.println("Client connected to MQTT Broker");
        }

        scheduledExecutorService.scheduleAtFixedRate(()-> {
            int i = random.nextInt(100);
            mqtt5BlockingClient.publishWith()
                    .topic("test")
                    .payload(("" + i).getBytes())
                    .qos(MqttQos.AT_LEAST_ONCE)
                    .send();
            int publishCount = counter.incrementAndGet();
            if (publishCount % 100 == 0) {
                System.out.println("Sent " + publishCount + " publishes.");
            }
        }, 100, 100, TimeUnit.MILLISECONDS);
    }

    public static void main(String[] args) throws InterruptedException {
        Producer producer = new Producer();
        producer.startPublishing();
        //run infinitely
        while (true){
            TimeUnit.SECONDS.sleep(10);
        }
    }

}
