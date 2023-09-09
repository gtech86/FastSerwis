package pl.grabowski.fastserwis.service.mapper;

import org.mapstruct.Mapper;
import pl.grabowski.fastserwis.dto.StatusDTO;
import pl.grabowski.fastserwis.model.Status;

@Mapper(componentModel = "spring")
public interface StatusMapper extends EntityMapper<Status, StatusDTO>{

}
