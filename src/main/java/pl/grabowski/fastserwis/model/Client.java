package pl.grabowski.fastserwis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "Clients")
public class Client {
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

    @OneToMany(mappedBy = "client")
    private Set<Devices> devices = new HashSet<>();

    public Client() {

    }
}
