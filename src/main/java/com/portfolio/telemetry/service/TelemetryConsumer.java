package com.portfolio.telemetry.service;

import com.portfolio.telemetry.model.Device;
import com.portfolio.telemetry.repository.DeviceRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TelemetryConsumer {

    private final DeviceRepository deviceRepository;

    public TelemetryConsumer(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    // Listens to the exact topic the Producer is sending to
    @KafkaListener(topics = "device-telemetry-events", groupId = "telemetry-group")
    public void consumeDeviceEvent(Device device) {
        System.out.println("Consumed from Kafka Queue. Saving to Postgres...");
        deviceRepository.save(device);
    }
}