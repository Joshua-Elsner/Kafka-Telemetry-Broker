package com.portfolio.telemetry.controller;

import com.portfolio.telemetry.model.Device;
import com.portfolio.telemetry.repository.DeviceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/telemetry")
public class TelemetryController {

    private final DeviceRepository deviceRepository;

    // Standard constructor-based dependency injection
    public TelemetryController(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    // Opens a POST endpoint at localhost:8080/api/v1/telemetry/device
    @PostMapping("/device")
    public ResponseEntity<Device> registerDevice(@RequestBody Device device) {
        // Saves the incoming JSON directly to PostgreSQL
        Device savedDevice = deviceRepository.save(device);
        return new ResponseEntity<>(savedDevice, HttpStatus.CREATED);
    }
}