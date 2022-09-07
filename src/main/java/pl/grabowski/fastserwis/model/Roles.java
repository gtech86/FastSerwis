package pl.grabowski.fastserwis.model;

import lombok.Getter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Table(name = "Roles")
public class Roles {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "Role_id")
    private Integer roleId;

    @Column(name = "Role_name")
    private String roleName;

    @OneToMany(mappedBy = "roles")
    private Set<Employees> employees;
}
