package pl.fastserwis.fastserwis.model;

import lombok.Getter;
import javax.persistence.*;

@Entity
@Getter
@Table(name = "Clients")
public class Clients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Client_id")
    private Float clientId;

    @Column(name = "First_name")
    private String firstName;

    @Column(name = "Last_name")
    private String lastName;

    @Column(name = "Street")
    private String street;

    @Column(name = "Street_number")
    private Integer streetNumber;

    @Column(name = "Flat_number")
    private Integer flatNumber;

    @Column(name = "Postal_code")
    private String postalCode;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Mail")
    private String mail;

    @ManyToOne
    @JoinTable(name = "Repair_orders",
            joinColumns = {@JoinColumn(name = "Order_id", nullable = false)}
    )
    private RepairOrders repairOrders;
}
