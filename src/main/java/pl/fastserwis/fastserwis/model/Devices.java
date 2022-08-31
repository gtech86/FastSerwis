package pl.fastserwis.fastserwis.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "Devices")
public class Devices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Device_id")
    private Float deviceId;

    @Column(name = "Model")
    private String model;

    @Column(name = "Serial_number")
    private String serialNumber;

    @Column(name = "Description")
    private String description;

    @OneToMany(mappedBy = "Devices")
    private Set<Category> category;

    @ManyToOne
    @JoinTable(name = "Repair_orders",
            joinColumns = {@JoinColumn(name = "Order_id", nullable = false)}
    )
    private RepairOrders repairOrders;

}
