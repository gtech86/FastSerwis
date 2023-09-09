package pl.grabowski.fastserwis.dto.device;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class DeviceSearchRequestDTO {
    private Long deviceId;

    private String producer;

    private String serialNumber;

    private String model;

    private Long category;

    public DeviceSearchRequestDTO(String producer, String serialNumber, String model, Long category) {
        this.producer = producer;
        this.serialNumber = serialNumber;
        this.model = model;
        this.category = category;
    }
}
