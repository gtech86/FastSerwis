package pl.grabowski.fastserwis.dto;

import java.sql.Timestamp;

public interface RepairOrdersSimpleResponse {
    Long getOrderId();
    String getCategoryName();
    String getLastName();
    Timestamp getOrderDate();
    Timestamp getExpectedEndDate();
    String getFaultDescription();
    String getStatusName();
}
