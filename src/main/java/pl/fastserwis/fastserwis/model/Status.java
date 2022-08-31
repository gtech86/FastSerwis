package pl.fastserwis.fastserwis.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "Status")
public class Status {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "Status_id")
    private Integer statusId;

    @Column(name = "Status_name")
    private String statusName;

    @ManyToOne
    @JoinTable(name = "Repair_orders",
            joinColumns = {@JoinColumn(name = "Order_id", nullable = false)}
    )
    private RepairOrders repairOrders;
}
