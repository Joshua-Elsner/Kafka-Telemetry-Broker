package com.portfolio.telemetry.service;

import com.portfolio.telemetry.model.Device;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TelemetryProducer {

    private static final String TOPIC = "device-telemetry-events";
    private final KafkaTemplate<String, Device> kafkaTemplate;

    public TelemetryProducer(KafkaTemplate<String, Device> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendDeviceEvent(Device device) {
        // Send the payload to the Kafka broker
        kafkaTemplate.send(TOPIC, device);
        System.out.println("Published to Kafka Queue: " + device.getDeviceType());
    }
}