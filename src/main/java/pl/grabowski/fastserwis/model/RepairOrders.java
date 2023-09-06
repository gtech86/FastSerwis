package pl.grabowski.fastserwis.model;

import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "Repair_orders")
public class RepairOrders {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "Order_id")
    private Long orderId;

    @Column(name = "Order_date")
    private LocalDateTime orderDate;

    @Column(name = "End_date")
    private LocalDateTime endDate;

    @Column(name = "Expected_end_date")
    private LocalDateTime expectedEndDate;

    @Column(name = "Fault_description")
    private String faultDescription;

    @Column(name = "Repair_description")
    private String repairDescription;

    @Column(name = "Charger")
    private Boolean charger;

    @Column(name = "Repair_price")
    private BigDecimal repairPrice;

    @Column(name = "Parts_price")
    private BigDecimal partsPrice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Device_id")
    private Devices devices;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Employee_id")
    private Employee employees;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Status_id")
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Order_type_id")
    private OrderTypes orderType;
}
