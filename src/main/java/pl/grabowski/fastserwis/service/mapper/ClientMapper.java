package pl.grabowski.fastserwis.service.mapper;

import org.mapstruct.Mapper;
import pl.grabowski.fastserwis.dto.ClientDTO;
import pl.grabowski.fastserwis.dto.DeviceDTO;
import pl.grabowski.fastserwis.model.Client;
import pl.grabowski.fastserwis.model.Devices;

@Mapper(componentModel = "spring")
public interface ClientMapper extends EntityMapper<Client, ClientDTO>{
}
