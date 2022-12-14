package pl.grabowski.fastserwis.model;

import lombok.Getter;
import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Table(name = "Employees")
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Employee_id")
    private Long employeeId;

    @Column(name = "First_name")
    private String firstName;

    @Column(name = "Last_name")
    private String lastName;

    @Column(name = "Username")
    private String username;

    @Column(name = "Password")
    private String password;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Mail")
    private String mail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Role_id")
    private Roles roles;

    @OneToMany(mappedBy = "employees")
    private Set<RepairOrders> repairOrders;


}
