package pl.grabowski.fastserwis.dto.client;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.grabowski.fastserwis.dto.device.DeviceDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ClientDTO {

    private Long clientId;

    private String firstName;

    private String lastName;

    private String street;

    private Integer streetNumber;

    private Integer flatNumber;

    private String postalCode;

    private String city;

    private String phone;

    private String mail;

    private List<DeviceDTO> devices;
}
