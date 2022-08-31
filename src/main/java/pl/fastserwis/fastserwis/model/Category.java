package pl.fastserwis.fastserwis.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "Categories")
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "Category_id")
    private Integer categoryId;

    @Column(name = "Category_name")
    private String categoryName;

    @ManyToOne
    @JoinTable(name = "Devices",
            joinColumns = {@JoinColumn(name = "Device_id", nullable = false)}
    )
    private Devices devices;
}
