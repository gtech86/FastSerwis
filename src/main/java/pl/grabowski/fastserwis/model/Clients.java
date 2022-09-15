package pl.grabowski.fastserwis.model;

import lombok.Getter;
import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Table(name = "Clients")
public class Clients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Client_id")
    private Long clientId;

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

    @Column(name = "City")
    private String city;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Mail")
    private String mail;

    @OneToMany(mappedBy = "clients")
    private Set<RepairOrders> repairOrders;
}
