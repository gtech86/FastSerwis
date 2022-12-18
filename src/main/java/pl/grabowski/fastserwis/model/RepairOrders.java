package pl.grabowski.fastserwis.model;

import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Getter
@Table(name = "Repair_orders")
public class RepairOrders {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "Order_id")
    private Long orderId;

    @Column(name = "Order_date")
    private Timestamp orderDate;

    @Column(name = "End_date")
    private Timestamp endDate;

    @Column(name = "Expected_end_date")
    private Timestamp expectedEndDate;

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
    @JoinColumn(name = "Client_id")
    private Client clients;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Employee_id")
    private Employees employees;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Status_id")
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Order_type_id")
    private OrderTypes orderType;
}
