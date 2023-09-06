package pl.grabowski.fastserwis.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "Categories")
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "Category_id")
    private Long categoryId;

    @Column(name = "Category_name")
    private String categoryName;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Devices> devices;

    public void removeDevice(Devices devices){
        this.devices.remove(devices);
    }
}
