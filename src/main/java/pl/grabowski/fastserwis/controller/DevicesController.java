package pl.grabowski.fastserwis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.grabowski.fastserwis.dto.DeviceCreateRequestDTO;
import pl.grabowski.fastserwis.dto.DeviceDTO;
import pl.grabowski.fastserwis.repository.CategoryRepo;
import pl.grabowski.fastserwis.repository.ClientRepo;
import pl.grabowski.fastserwis.service.ClientsService;
import pl.grabowski.fastserwis.service.DeviceService;
import pl.grabowski.fastserwis.service.mapper.CategoryMapper;
import pl.grabowski.fastserwis.service.mapper.DeviceMapper;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/device")
@RequiredArgsConstructor
public class DevicesController {
    private final DeviceService deviceService;
    private final DeviceMapper deviceMapper;
    private final ClientsService clientsService;
    private final CategoryRepo categoryRepo;
    private final CategoryMapper categoryMapper;

    @GetMapping("/add/{clientId}")
    public String createDeviceByClientId(Model model,
                                         @PathVariable String clientId) {
        model.addAttribute("clientId", Integer.valueOf(clientId));
        model.addAttribute("deviceDTO", new DeviceCreateRequestDTO());
        model.addAttribute("categories", categoryMapper.toDto(categoryRepo.findAll()));
        return "/device/CreateDevice";
    }

    @PostMapping("/add")
    public String createDevice(@Valid DeviceCreateRequestDTO newDeviceDTO,  @RequestParam Long clientId) {
        DeviceDTO addedDevice = deviceService.createDevice(clientId, newDeviceDTO);
        return "redirect:/device/"+addedDevice.getDeviceId();
    }

    @GetMapping("/edit")
    public String editClient(
            @RequestParam Long deviceId,
            Model model){
        model.addAttribute("deviceDTO", deviceService.getDeviceById(deviceId));
        model.addAttribute("categories", categoryMapper.toDto(categoryRepo.findAll()));
        return "/device/UpdateDevice";

    }

    @PostMapping("/edit")
    public String updateDevice(@Valid DeviceCreateRequestDTO updateDeviceDTO,  @RequestParam String deviceId) {
        deviceService.updateDevice(updateDeviceDTO);
        return "redirect:/device/"+deviceId;
    }

    @GetMapping("/{deviceId}")
    public String getDeviceById(Model model,
                                @PathVariable Long deviceId) {
        DeviceDTO device = deviceService.getDeviceById(deviceId);

        model.addAttribute("device", device);
        model.addAttribute("client", deviceService.getClientByDeviceId(deviceId));
            return "/device/Device";
    }

    @GetMapping("/find")
    public String getClientByPersonalData(
            Model model,
            @RequestParam(defaultValue = "1") int page)
    {
        Page<DeviceDTO> allDevices = deviceService.getAllDevices(page);
        List<DeviceDTO> devices = allDevices
                .stream()
                .collect(Collectors.toList());
        model.addAttribute("devices", devices);
        model.addAttribute("totalPages", allDevices.getTotalPages());
        model.addAttribute("currentPage", page);
        return "/device/SearchDevice";
    }

}
