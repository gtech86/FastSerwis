package pl.fastserwis.fastserwis.model;

import lombok.Getter;

import javax.persistence.*;

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

    @ManyToOne
    @JoinTable(name = "Categories",
            joinColumns = {@JoinColumn(name = "Category_id", nullable = false)}
    )
    private Employees employees;
}
