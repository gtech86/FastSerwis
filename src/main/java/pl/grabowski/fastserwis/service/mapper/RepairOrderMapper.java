package pl.grabowski.fastserwis.service.mapper;

import org.mapstruct.Mapper;
import pl.grabowski.fastserwis.dto.RepairOrderCreateRequest;
import pl.grabowski.fastserwis.dto.RepairOrderDTO;
import pl.grabowski.fastserwis.dto.order.RepairOrderSearchRequest;
import pl.grabowski.fastserwis.dto.order.RepairOrderUpdateRequest;
import pl.grabowski.fastserwis.model.RepairOrder;

@Mapper(componentModel = "spring")
public interface RepairOrderMapper extends EntityMapper<RepairOrder, RepairOrderCreateRequest>{
    RepairOrderDTO toRepairOrderDTO(RepairOrder repairOrder);
    RepairOrder toUpdateEntity(RepairOrderUpdateRequest repairOrderUpdateRequest);
    RepairOrderUpdateRequest toRepairUpdateDTO(RepairOrder repairOrder);

    RepairOrder toSearchDTO(RepairOrderSearchRequest repairOrderSearchRequest);
}
