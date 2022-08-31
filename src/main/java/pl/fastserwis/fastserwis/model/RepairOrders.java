package pl.fastserwis.fastserwis.model;

import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;

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

    @OneToMany(mappedBy = "Repair_orders")
    private Set<Devices> devices;

    @OneToMany(mappedBy = "Repair_orders")
    private Set<Clients> clients;

    @OneToMany(mappedBy = "Repair_orders")
    private Set<Employees> employees;

    @OneToMany(mappedBy = "Repair_orders")
    private Set<Status> status;

}
