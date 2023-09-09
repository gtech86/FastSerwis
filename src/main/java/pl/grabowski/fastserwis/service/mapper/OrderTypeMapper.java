package pl.grabowski.fastserwis.service.mapper;

import org.mapstruct.Mapper;
import pl.grabowski.fastserwis.dto.StatusDTO;
import pl.grabowski.fastserwis.dto.order.OrderTypeDTO;
import pl.grabowski.fastserwis.model.OrderTypes;
import pl.grabowski.fastserwis.model.Status;

@Mapper(componentModel = "spring")
public interface OrderTypeMapper extends EntityMapper<OrderTypes, OrderTypeDTO>{

}
