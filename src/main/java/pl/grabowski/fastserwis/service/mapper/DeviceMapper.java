package pl.grabowski.fastserwis.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.grabowski.fastserwis.dto.client.DeviceCreateRequestDTO;
import pl.grabowski.fastserwis.dto.device.DeviceDTO;
import pl.grabowski.fastserwis.dto.device.DeviceSearchRequestDTO;
import pl.grabowski.fastserwis.dto.device.DeviceUpdateDTO;
import pl.grabowski.fastserwis.model.Devices;

@Mapper(componentModel = "spring")
public interface DeviceMapper extends EntityMapper<Devices, DeviceDTO>{
    @Override
    @Mapping(target = "category", ignore = true)
    Devices toEntity(DeviceDTO dto);

    @Mapping(target = "category", ignore = true)
    Devices toEntity(DeviceCreateRequestDTO deviceCreateRequestDTO);

    @Mapping(target = "category", ignore = true)
    Devices toSearchDTO(DeviceSearchRequestDTO searchRequestDTO);

    DeviceUpdateDTO toUpdateDeviceDTO(Devices devices);

    Devices toUpdateDevice(DeviceUpdateDTO deviceUpdateDTO);
}
