package pl.grabowski.fastserwis.dto.order;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public interface RepairOrdersSimpleResponse {
    Long getOrderId();
    String getCategoryName();
    String getLastName();
    LocalDateTime getOrderDate();
    LocalDateTime getExpectedEndDate();
    String getFaultDescription();
    String getStatusName();
}
