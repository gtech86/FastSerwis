package pl.grabowski.fastserwis.dto.order;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.grabowski.fastserwis.model.OrderTypes;
import pl.grabowski.fastserwis.model.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class RepairOrderUpdateRequest {

    private Long orderId;

    private LocalDateTime orderDate;

    private LocalDateTime endDate;

    private LocalDateTime expectedEndDate;

    private String faultDescription;

    private String repairDescription;

    private Boolean charger;

    private BigDecimal repairPrice;

    private BigDecimal partsPrice;

    private Long statusId;

    private Long employeeId;

    private Long orderTypeId;
}
