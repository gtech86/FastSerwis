package pl.grabowski.fastserwis.dto;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@RequiredArgsConstructor
public class RepairOrdersExtendedResponse {
    private final Long orderId;
    private final Timestamp orderDate;
    private final Timestamp endDate;
    private final Timestamp expectedEndDate;
    private final String faultDescription;
    private final String repairDescription;
    private final Boolean charger;
    private final BigDecimal repairPrice;
    private final BigDecimal partsPrice;
    private final String orderType;
    private final String employeeFirstName;
    private final String employeeLastName;
    private final String employeePhone;
    private final String employeeMail;
    private final String statusName;
}
