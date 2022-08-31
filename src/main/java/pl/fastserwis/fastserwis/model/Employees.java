package pl.fastserwis.fastserwis.model;

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
    private Float employeeId;

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

    @OneToMany(mappedBy = "Employees")
    private Set<WorkPositions> workPositions;

    @OneToMany(mappedBy = "Employees")
    private Set<Roles> roles;

    @ManyToOne
    @JoinTable(name = "Repair_orders",
            joinColumns = {@JoinColumn(name = "Order_id", nullable = false)}
    )
    private RepairOrders repairOrders;

}
