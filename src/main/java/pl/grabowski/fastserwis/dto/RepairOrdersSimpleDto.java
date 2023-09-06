package pl.grabowski.fastserwis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
public class RepairOrdersSimpleDto {
    private Long orderId;
    private String categoryName;
    private String lastName;
    private LocalDateTime orderDate;
    private LocalDateTime expectedEndDate;
    private String faultDescription;
    private String statusName;
}
