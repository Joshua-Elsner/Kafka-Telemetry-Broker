package com.portfolio.telemetry.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.UUID;

@Entity
@Table(name = "devices")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // Ensures the field is not null and not just empty spaces
    @NotBlank(message = "Device type is required")
    @Column(name = "device_type", nullable = false)
    private String deviceType;

    // Forces the status to strictly match one of these three exact strings
    @NotBlank(message = "Status is required")
    @Pattern(regexp = "^(ACTIVE|INACTIVE|MAINTENANCE)$", message = "Invalid status. Must be ACTIVE, INACTIVE, or MAINTENANCE")
    @Column(name = "status", nullable = false)
    private String status;

    // Default Constructor required by JPA
    public Device() {}

    public Device(String deviceType, String status) {
        this.deviceType = deviceType;
        this.status = status;
    }

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getDeviceType() { return deviceType; }
    public void setDeviceType(String deviceType) { this.deviceType = deviceType; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}