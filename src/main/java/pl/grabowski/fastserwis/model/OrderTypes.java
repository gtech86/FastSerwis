package pl.grabowski.fastserwis.model;

import lombok.Getter;
import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Table(name = "Order_types")
public class OrderTypes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Order_type_id")
    private Integer orderTypeId;

    @Column(name = "Type_name")
    private String typeName;

    @OneToMany(mappedBy = "orderType")
    private Set<RepairOrders> repairOrders;
}
