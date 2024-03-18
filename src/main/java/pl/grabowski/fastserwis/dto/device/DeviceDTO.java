package pl.grabowski.fastserwis.dto.device;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.grabowski.fastserwis.dto.IdNameCategoryDTO;

@Getter
@Setter
@NoArgsConstructor
public class DeviceDTO {
    private Long deviceId;

    private String producer;

    private String serialNumber;

    private String model;

    private IdNameCategoryDTO category;
}
