package pl.grabowski.fastserwis.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

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

    @OneToMany(mappedBy = "categories")
    private Set<Devices> devices;

    public Category() {
    }

}
