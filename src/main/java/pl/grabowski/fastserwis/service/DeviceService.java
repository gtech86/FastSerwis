package pl.grabowski.fastserwis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.grabowski.fastserwis.dto.client.ClientDTO;
import pl.grabowski.fastserwis.dto.client.DeviceCreateRequestDTO;
import pl.grabowski.fastserwis.dto.device.DeviceDTO;
import pl.grabowski.fastserwis.dto.device.DeviceSearchRequestDTO;
import pl.grabowski.fastserwis.dto.device.DeviceUpdateDTO;
import pl.grabowski.fastserwis.model.Category;
import pl.grabowski.fastserwis.model.Client;
import pl.grabowski.fastserwis.model.Devices;
import pl.grabowski.fastserwis.repository.CategoryRepo;
import pl.grabowski.fastserwis.repository.ClientRepo;
import pl.grabowski.fastserwis.repository.DeviceRepo;
import pl.grabowski.fastserwis.service.mapper.ClientMapper;
import pl.grabowski.fastserwis.service.mapper.DeviceMapper;

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

    private static final ExampleMatcher SEARCH_CONDITIONS_MATCH_ALL = ExampleMatcher
            .matching()
            .withMatcher("producer", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("model", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("serialNumber", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withIgnorePaths("deviceId");

    public Page<DeviceDTO> getAllDevices(int page) {
        return deviceRepo.findAll(PageRequest.of(page, pageSize)).map(
                deviceMapper::toDto
        );
    }
    public ClientDTO getClientByDeviceId(Long deviceId){
        Devices deviceDb = deviceRepo.getDeviceById(deviceId);
        Client client = clientRepo.getClientByClientId(deviceDb.getClient().getClientId()).orElseThrow();
        return clientMapper.toDto(client);
    }

    public DeviceDTO getDeviceById(Long id){
        return deviceMapper.toDto(deviceRepo.getDeviceById(id));
    }

    public DeviceUpdateDTO getDeviceToUpdateById(Long id){
        return deviceMapper.toUpdateDeviceDTO(deviceRepo.getDeviceById(id));
    }

    public DeviceDTO createDevice(Long clientId, DeviceCreateRequestDTO newDeviceDTO) {
        Client client = clientRepo.getClientByClientId(clientId).orElseThrow();
        Category category = categoryRepo.getByIdOrThrow(newDeviceDTO.getCategory());
        Devices newDevice = deviceMapper.toEntity(newDeviceDTO);
        newDevice.setClient(client);
        newDevice.setCategory(category);
        return deviceMapper.toDto(deviceRepo.save(newDevice));
    }

    public void updateDevice(DeviceUpdateDTO updateDeviceDTO) {
        Devices deviceDb = deviceRepo.getDeviceById(updateDeviceDTO.getDeviceId());
        Devices newDevice = deviceMapper.toUpdateDevice(updateDeviceDTO);
        newDevice.setCategory(categoryRepo.getByIdOrThrow(updateDeviceDTO.getCategoryId()));
        deviceDb.update(newDevice);
    }

    @Transactional
    public Page<Devices> searchDevice(DeviceSearchRequestDTO searchDeviceDTO, int page, String sort) {
        Devices deviceDb = deviceMapper.toSearchDTO(searchDeviceDTO);

        Example<Devices> example = Example.of(deviceDb, SEARCH_CONDITIONS_MATCH_ALL);

        return deviceRepo.findAll(example, PageRequest.of(page - 1, pageSize, Sort.by(sort)));

    }
}
