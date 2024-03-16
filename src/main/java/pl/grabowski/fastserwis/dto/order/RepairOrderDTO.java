package pl.grabowski.fastserwis.dto.order;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.grabowski.fastserwis.dto.SimpleEmployeeDTO;
import pl.grabowski.fastserwis.dto.StatusDTO;
import pl.grabowski.fastserwis.dto.device.DeviceDTO;
import pl.grabowski.fastserwis.model.OrderTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class RepairOrderDTO {

    private Long orderId;

    private LocalDateTime orderDate;

    private LocalDateTime endDate;

    private LocalDateTime expectedEndDate;

    private String faultDescription;

    private String repairDescription;

    private Boolean charger;

    private BigDecimal repairPrice = BigDecimal.ZERO;

    private BigDecimal partsPrice = BigDecimal.ZERO;

    private DeviceDTO devices;

    private SimpleEmployeeDTO employees;

    private StatusDTO status;

    private OrderTypes orderType;
    private String serialNumber;
}
