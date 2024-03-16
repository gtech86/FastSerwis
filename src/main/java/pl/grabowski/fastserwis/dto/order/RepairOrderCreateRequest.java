package pl.grabowski.fastserwis.dto.order;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.grabowski.fastserwis.model.Devices;
import pl.grabowski.fastserwis.model.Employee;
import pl.grabowski.fastserwis.model.OrderTypes;
import pl.grabowski.fastserwis.model.Status;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RepairOrderCreateRequest {

    private Long orderId;

    private LocalDateTime orderDate;

    private LocalDateTime endDate;

    private LocalDateTime expectedEndDate;

    private String faultDescription;

    private String repairDescription;

    private Boolean charger;

    private BigDecimal repairPrice;

    private BigDecimal partsPrice;

    private Status status;

    private Long employeeId;

    private Long orderTypeId;
}
