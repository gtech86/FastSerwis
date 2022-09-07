package pl.grabowski.fastserwis.model;

import lombok.Getter;

import javax.persistence.*;
import java.util.Set;

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

    @OneToMany(mappedBy = "status")
    private Set<RepairOrders> repairOrders;
}
