package com.portfolio.telemetry.controller;

import com.portfolio.telemetry.model.Device;
import com.portfolio.telemetry.service.TelemetryProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/telemetry")
public class TelemetryController {

    private final TelemetryProducer telemetryProducer;

    // Injecting the Kafka Producer instead of the Database Repository
    public TelemetryController(TelemetryProducer telemetryProducer) {
        this.telemetryProducer = telemetryProducer;
    }

    @PostMapping("/device")
    public ResponseEntity<String> registerDevice(@Valid @RequestBody Device device) {
        // Drop the data into Kafka and immediately return a 202 Accepted response
        telemetryProducer.sendDeviceEvent(device);
        return new ResponseEntity<>("Event Queued Successfully", HttpStatus.ACCEPTED);
    }
}