package pl.grabowski.fastserwis.dto.order;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class RepairOrderSearchRequest {

    private Long orderId;

    private LocalDateTime orderDate;

    private LocalDateTime endDate;

    private LocalDateTime expectedEndDate;

    private Long statusId;

    private Long employeeId;

    private Long orderTypeId;
}
