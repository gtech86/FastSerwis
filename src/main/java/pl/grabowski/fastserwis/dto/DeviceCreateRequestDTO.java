package pl.grabowski.fastserwis.dto;

import lombok.Data;

@Data
public class DeviceCreateRequestDTO {
    private Long deviceId;

    private String producer;

    private String serialNumber;

    private String model;

    private Long category;
}
