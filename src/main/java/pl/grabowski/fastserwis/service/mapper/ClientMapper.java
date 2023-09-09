package pl.grabowski.fastserwis.service.mapper;

import org.mapstruct.Mapper;
import pl.grabowski.fastserwis.dto.client.ClientDTO;
import pl.grabowski.fastserwis.model.Client;

@Mapper(componentModel = "spring")
public interface ClientMapper extends EntityMapper<Client, ClientDTO>{
}
