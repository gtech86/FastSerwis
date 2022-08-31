package pl.fastserwis.fastserwis.model;

import lombok.Getter;

import javax.persistence.*;

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

    @ManyToOne
    @JoinTable(name = "Categories",
            joinColumns = {@JoinColumn(name = "Category_id", nullable = false)}
    )
    private Employees employees;
}
