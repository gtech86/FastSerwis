package pl.grabowski.fastserwis.dto.order;

import java.math.BigDecimal;
import java.sql.Timestamp;

public interface RepairOrderExtendedResponse {
    Long getOrderId();
    Timestamp getOrderDate();
    Timestamp getEndDate();
    Timestamp getExpectedEndDate();
    String getFaultDescription();
    String getRepairDescription();
    Boolean getCharger();
    BigDecimal getRepairPrice();
    BigDecimal getPartsPrice();
    String getOrderType();
    String getStatusName();
    String getDeviceId();
    String getDeviceProducer();
    String getDeviceModel();
    String getDeviceSerialNumber();
    String getEmployeeFirstName();
    String getEmployeeLastName();
    String getEmployeePhone();
    String getClientId();
    String getClientFirstName();
    String getClientLastName();
    String getClientPhone();
}
