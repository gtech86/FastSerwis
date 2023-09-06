package pl.grabowski.fastserwis.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.grabowski.fastserwis.dto.*;
import pl.grabowski.fastserwis.model.Category;
import pl.grabowski.fastserwis.model.Client;
import pl.grabowski.fastserwis.model.Devices;
import pl.grabowski.fastserwis.model.RepairOrders;
import pl.grabowski.fastserwis.repository.CategoryRepo;
import pl.grabowski.fastserwis.repository.ClientRepo;
import pl.grabowski.fastserwis.repository.DeviceRepo;
import pl.grabowski.fastserwis.repository.RepairOrdersRepo;
import pl.grabowski.fastserwis.service.mapper.ClientMapper;
import pl.grabowski.fastserwis.service.mapper.DeviceMapper;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class DeviceService {
    @Value("${page.size}")
    private int pageSize;
    private final DeviceRepo deviceRepo;
    private final DeviceMapper deviceMapper;
    private final ClientRepo clientRepo;
    private final ClientMapper clientMapper;
    private final CategoryRepo categoryRepo;

    public Page<DeviceDTO> getAllDevices(int page) {
        return deviceRepo.findAllBy(PageRequest.of(page, pageSize)).map(
                deviceMapper::toDto
        );
    }
    public ClientDTO getClientByDeviceId(Long deviceId){
        Devices deviceDb = deviceRepo.getByIdOrThrow(deviceId);
        Client client = clientRepo.getClientByClientId(deviceDb.getClient().getClientId()).orElseThrow();
        return clientMapper.toDto(client);
    }

    public DeviceDTO getDeviceById(Long id){
        return deviceMapper.toDto(deviceRepo.getDeviceById(id));
    }

    public DeviceDTO createDevice(Long clientId, DeviceCreateRequestDTO newDeviceDTO) {
        Client client = clientRepo.getClientByClientId(clientId).orElseThrow();
        Category category = categoryRepo.getByIdOrThrow(newDeviceDTO.getCategory());
        Devices newDevice = deviceMapper.toEntity(newDeviceDTO);
        newDevice.setClient(client);
        newDevice.setCategory(category);
        return deviceMapper.toDto(deviceRepo.save(newDevice));
    }

    public void updateDevice(DeviceCreateRequestDTO updateDeviceDTO) {
        Devices deviceDb = deviceRepo.getByIdOrThrow(updateDeviceDTO.getDeviceId());
        Category category = categoryRepo.getByIdOrThrow(deviceDb.getCategory().getCategoryId());
        Devices newDevice = deviceMapper.toEntity(updateDeviceDTO);
        category.removeDevice(deviceDb);
        Category newCategory = categoryRepo.getByIdOrThrow(updateDeviceDTO.getCategory());
        deviceDb.update(newDevice);
        newDevice.setCategory(newCategory);
    }
}
