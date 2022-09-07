package pl.grabowski.fastserwis.model;

import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Getter
@Table(name = "Repair_orders")
public class RepairOrders {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "Order_id")
    private Long orderId;

    @Column(name = "Order_date")
    private Date orderDate;

    @Column(name = "End_date")
    private Date endDate;

    @Column(name = "Fault_description")
    private String faultDescription;

    @Column(name = "Charger")
    private Boolean charger;

    @Column(name = "Repair_price")
    private BigDecimal repairPrice;

    @Column(name = "Parts_price")
    private BigDecimal partsPrice;

    @ManyToOne
    @JoinColumn(name = "Device_id")
    private Devices devices;

    @ManyToOne
    @JoinColumn(name = "Client_id")
    private Clients clients;

    @ManyToOne
    @JoinColumn(name = "Employee_id")
    private Employees employees;

    @ManyToOne
    @JoinColumn(name = "Status_id")
    private Status status;
}
