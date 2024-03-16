package pl.grabowski.fastserwis.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.grabowski.fastserwis.dto.order.RepairOrderCreateRequest;
import pl.grabowski.fastserwis.dto.order.RepairOrderDTO;
import pl.grabowski.fastserwis.dto.order.RepairOrderSearchRequest;
import pl.grabowski.fastserwis.dto.order.RepairOrderUpdateRequest;
import pl.grabowski.fastserwis.model.RepairOrder;

@Mapper(componentModel = "spring")
public interface RepairOrderMapper extends EntityMapper<RepairOrder, RepairOrderCreateRequest>{
    @Mapping(target = "repairPrice", defaultValue = "0.00")
    @Mapping(target = "partsPrice", defaultValue = "0.00")
    @Mapping(target = "serialNumber", source = "devices.serialNumber")
    RepairOrderDTO toRepairOrderDTO(RepairOrder repairOrder);
    RepairOrder toUpdateEntity(RepairOrderUpdateRequest repairOrderUpdateRequest);
    RepairOrderUpdateRequest toRepairUpdateDTO(RepairOrder repairOrder);

    RepairOrder toSearchDTO(RepairOrderSearchRequest repairOrderSearchRequest);
}
