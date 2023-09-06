package pl.grabowski.fastserwis.model;

import lombok.Getter;
import lombok.Setter;
import pl.grabowski.fastserwis.dto.DeviceCreateRequestDTO;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "Devices")
public class Devices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Device_id")
    private Long deviceId;

    @Column(name = "Producer")
    private String producer;

    @Column(name = "Serial_number")
    private String serialNumber;

    @Column(name = "Model")
    private String model;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "devices")
    private Set<RepairOrders> repairOrders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Client_id")
    private Client client;

    public Devices(){}

    public void update(Devices updateDevice){
        this.producer = updateDevice.getProducer();
        this.model = updateDevice.getModel();
        this.serialNumber = updateDevice.getSerialNumber();
    }
}
