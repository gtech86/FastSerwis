package pl.grabowski.fastserwis.controller;

import com.lowagie.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.WebContext;
import pl.grabowski.fastserwis.dto.RepairOrderCreateRequest;
import pl.grabowski.fastserwis.dto.RepairOrderDTO;
import pl.grabowski.fastserwis.dto.device.DeviceDTO;
import pl.grabowski.fastserwis.dto.device.DeviceSearchRequestDTO;
import pl.grabowski.fastserwis.dto.order.RepairOrderUpdateRequest;
import pl.grabowski.fastserwis.model.Devices;
import pl.grabowski.fastserwis.model.RepairOrder;
import pl.grabowski.fastserwis.repository.OrderTypesRepo;
import pl.grabowski.fastserwis.repository.RepairOrdersRepo;
import pl.grabowski.fastserwis.repository.StatusRepo;
import pl.grabowski.fastserwis.service.ClientsService;
import pl.grabowski.fastserwis.service.DeviceService;
import pl.grabowski.fastserwis.service.EmployeeService;
import pl.grabowski.fastserwis.service.RepairOrdersService;
import pl.grabowski.fastserwis.service.mapper.RepairOrderMapper;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {
    @Value("${page.size}")
    private int pageSize;
    private final RepairOrdersService ordersService;
    private final RepairOrdersRepo ordersRepo;
    private final DeviceService deviceService;
    private final ClientsService clientsService;
    private final OrderTypesRepo orderTypesRepo;
    private final EmployeeService employeeService;
    private final StatusRepo statusRepo;
    private final RepairOrderMapper repairOrderMapper;
    private final ServletContext servletContext;

    @GetMapping("/add")
    public String getRepairOrderForm(Model model,
                                     @RequestParam String deviceId,
                                     @RequestParam String clientId){
        model.addAttribute("orderDTO", new RepairOrderCreateRequest());
        model.addAttribute("device", deviceService.getDeviceById(Long.parseLong(deviceId)));
        model.addAttribute("client", clientsService.getClientDtoById(Long.parseLong(clientId)));
        model.addAttribute("orderTypes", orderTypesRepo.findAll());
        model.addAttribute("employees", employeeService.getAllSimpleEmployee());
        return "/order/CreateOrder";
    }

    @PostMapping("/add")
    public String addNewOrder(@Valid RepairOrderCreateRequest newOrderDTO,
                              @RequestParam String deviceId,
                              @RequestParam String clientId) {

        RepairOrderDTO addedRepairOrder =  ordersService.addOrder(Long.parseLong(deviceId), Long.parseLong(clientId), newOrderDTO);
        return "redirect:/order/"+addedRepairOrder.getOrderId();
    }

    @GetMapping("/edit")
    public String editOrder(Model model,
                            @RequestParam String deviceId,
                            @RequestParam String clientId,
                            @RequestParam String orderId){

        model.addAttribute("orderDTO", ordersService.getRepairOrderToUpdate(Long.parseLong(orderId)));
        model.addAttribute("orderId", orderId);
        model.addAttribute("orderTypeName", ordersRepo.findById(Long.parseLong(orderId)).orElseThrow().getOrderType().getTypeName());
        model.addAttribute("device", deviceService.getDeviceById(Long.parseLong(deviceId)));
        model.addAttribute("client", clientsService.getClientDtoById(Long.parseLong(clientId)));
        model.addAttribute("orderTypes", orderTypesRepo.findAll());
        model.addAttribute("employees", employeeService.getAllSimpleEmployee());
        model.addAttribute("statuses", statusRepo.findAll());
        return "/order/UpdateOrder";

    }

    @PostMapping("/edit")
    public String updateOrder(@Valid RepairOrderUpdateRequest updateOrderDTO,
                              @RequestParam String deviceId,
                              @RequestParam String clientId,
                              @RequestParam String orderId) {

        RepairOrderDTO updatedRepairOrder =  ordersService.updateOrder(updateOrderDTO);
        return "redirect:/orders/"+updatedRepairOrder.getOrderId();
    }

    @GetMapping("/find")
    public String searchOrders(
            Model model,
            @RequestParam(required = false, defaultValue = "")  String serialNumber,
            @RequestParam(required = false, defaultValue = "")  String orderId,
            @RequestParam(required = false, defaultValue = "")  String employeeId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "orderDate") String sorting)
    {
       Page<RepairOrder> allOrders = ordersService.searchOrder(orderId, serialNumber, employeeId, page, sorting);

        model.addAttribute("orders", allOrders.stream().map(repairOrderMapper::toRepairOrderDTO).collect(Collectors.toList()));
        model.addAttribute("employees", employeeService.getAllSimpleEmployee());
        model.addAttribute("totalPages", allOrders.getTotalPages());
        model.addAttribute("currentPage", pageSize);
        return "/order/searchOrder";
    }

    @GetMapping("/download/{orderId}")
    public String getFile(Model model, @PathVariable(required = true) Long orderId){
        RepairOrderDTO extendedRepairOrders = ordersService.getExtendedRepairOrders(orderId);

        model.addAttribute("client", clientsService.getClientByDeviceId(extendedRepairOrders.getDevices().getDeviceId()));
        model.addAttribute("order", extendedRepairOrders);

        return "/PdfTemplate";
    }

    @GetMapping("{orderId}")
    public String getOrderById(Model model, @PathVariable(required = true) Long orderId){
        var extendedOrder = ordersService.getExtendedRepairOrders(orderId);
            model.addAttribute("client", clientsService.getClientByDeviceId(extendedOrder.getDevices().getDeviceId()));
            model.addAttribute("order", extendedOrder);
            return "/order/searchOrderId";
    }

    public String getOrderByClientId(@PathVariable(required = true) Long clientId){
        return "";
    }

    @GetMapping("/search")
    public String getOrderByStatus(Model model, @RequestParam(required = false) String ordersStatus){
        var orders = ordersRepo.getRepairOrdersByStatus(ordersStatus);
        model.addAttribute("orders", orders);
        return "/order/searchOrderByStatus";
    }

}
