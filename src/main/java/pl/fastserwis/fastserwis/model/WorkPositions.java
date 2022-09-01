package pl.fastserwis.fastserwis.model;

import lombok.Getter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Table(name = "Work_positions")
public class WorkPositions {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "Position_id")
    private Integer positionId;

    @Column(name = "Position_name")
    private String positionName;

    @OneToMany(mappedBy = "workPositions")
    private Set<Employees> employees;
}
