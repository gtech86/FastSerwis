package pl.grabowski.fastserwis.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.grabowski.fastserwis.dto.DeviceCreateRequestDTO;
import pl.grabowski.fastserwis.dto.DeviceDTO;
import pl.grabowski.fastserwis.model.Devices;

@Mapper(componentModel = "spring")
public interface DeviceMapper extends EntityMapper<Devices, DeviceDTO>{
    @Override
    @Mapping(target = "category", ignore = true)
    Devices toEntity(DeviceDTO dto);

    @Mapping(target = "category", ignore = true)
    Devices toEntity(DeviceCreateRequestDTO deviceCreateRequestDTO);
}
