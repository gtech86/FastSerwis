package pl.grabowski.fastserwis.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "Employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Employee_id")
    private Long employeeId;

    @Column(name = "First_name")
    private String firstName;

    @Column(name = "Last_name")
    private String lastName;

    @Column(name = "Username", unique = true)
    private String username;

    @Column(name = "Password")
    private String password;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Mail")
    private String mail;

    @Column(name = "Block")
    private Boolean isBlocked;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Role_id")
    private Roles roles;

    @OneToMany(mappedBy = "employees")
    private Set<RepairOrder> repairOrders;

    public Employee() {
    }

    public void update(Employee employee) {
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.phone = employee.getPhone();
        this.mail = employee.getMail();
    }
}
