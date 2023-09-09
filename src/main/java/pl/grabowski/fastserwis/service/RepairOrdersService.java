package pl.grabowski.fastserwis.service;

import com.lowagie.text.DocumentException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;
import pl.grabowski.fastserwis.controller.OrdersController;
import pl.grabowski.fastserwis.dto.RepairOrderCreateRequest;
import pl.grabowski.fastserwis.dto.RepairOrderDTO;
import pl.grabowski.fastserwis.dto.RepairOrderExtendedResponse;
import pl.grabowski.fastserwis.dto.RepairOrdersSimpleDto;
import pl.grabowski.fastserwis.dto.device.DeviceSearchRequestDTO;
import pl.grabowski.fastserwis.dto.order.RepairOrderSearchRequest;
import pl.grabowski.fastserwis.dto.order.RepairOrderUpdateRequest;
import pl.grabowski.fastserwis.model.*;
import pl.grabowski.fastserwis.repository.*;
import pl.grabowski.fastserwis.service.mapper.RepairOrderMapper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RepairOrdersService {
    @Value("${page.size}")
    private int pageSize;
    private final RepairOrdersRepo ordersRepo;
    private final EmployeeRepo employeeRepo;
    private final ClientsService clientsService;
    private final DeviceRepo deviceRepo;
    private final RepairOrderMapper repairOrderMapper;
    private final StatusRepo statusRepo;
    private final SpringTemplateEngine templateEngine;
    private final OrderTypesRepo orderTypesRepo;


    private static final ExampleMatcher SEARCH_CONDITIONS_MATCH_ALL  = ExampleMatcher.matching();

    public List<RepairOrdersSimpleDto> getSimpleRepairOrders() {
        return ordersRepo.getSimpleRepairOrders();
    }

    public RepairOrderDTO getExtendedRepairOrders(Long orderId) {
        return repairOrderMapper.toRepairOrderDTO(ordersRepo.findById(orderId).orElseThrow());

    }

    public Optional<RepairOrder> getOrderById(Long orderId) {
        return ordersRepo.findById(orderId);
    }

    public RepairOrderDTO addOrder(long deviceId, long clientId, RepairOrderCreateRequest newOrderDTO) {
        RepairOrder repairOrderToAdd = repairOrderMapper.toEntity(newOrderDTO);
        Employee employeeDb = employeeRepo.findByEmployeeId(newOrderDTO.getEmployeeId());
        Devices deviceDb = deviceRepo.getDeviceById(deviceId);
        repairOrderToAdd.setOrderDate(LocalDateTime.now());
        repairOrderToAdd.setOrderType(orderTypesRepo.getByIdOrThrow(newOrderDTO.getOrderTypeId()));
        if(repairOrderToAdd.getOrderType().getTypeName().equals("ekspresowe")){
            repairOrderToAdd.setExpectedEndDate(LocalDateTime.now().plusDays(5));
        }
        else {
            repairOrderToAdd.setExpectedEndDate(LocalDateTime.now().plusDays(14));
        }
        repairOrderToAdd.setEmployees(employeeDb);
        repairOrderToAdd.setDevices(deviceDb);
        repairOrderToAdd.setStatus(statusRepo.getStatusByStatusNameIsLike("Nowe"));
        return repairOrderMapper.toRepairOrderDTO(ordersRepo.save(repairOrderToAdd));
    }

    public RepairOrderDTO updateOrder(RepairOrderUpdateRequest newOrderDTO) {
        RepairOrder orderToUpdate = ordersRepo.findById(newOrderDTO.getOrderId()).orElseThrow();
        orderToUpdate.update(newOrderDTO);
        Employee employeeDb = employeeRepo.findByEmployeeId(newOrderDTO.getEmployeeId());
        orderToUpdate.setEmployees(employeeDb);
        orderToUpdate.setStatus(statusRepo.findById(newOrderDTO.getStatusId()).orElseThrow());
        if(orderToUpdate.getStatus().getStatusName().equals("Zamknięte")){
            orderToUpdate.setEndDate(LocalDateTime.now());
        }
        return repairOrderMapper.toRepairOrderDTO(ordersRepo.save(orderToUpdate));
    }

    public RepairOrderUpdateRequest getRepairOrderToUpdate(Long repairOrderId){
        RepairOrder repairOrder = ordersRepo.findById(repairOrderId).orElseThrow();
        RepairOrderUpdateRequest repairUpdateDTO = repairOrderMapper.toRepairUpdateDTO(repairOrder);
        repairUpdateDTO.setStatusId(repairOrder.getStatus().getStatusId());
        repairUpdateDTO.setEmployeeId(repairOrder.getEmployees().getEmployeeId());
        return repairUpdateDTO;
    }

    @Transactional
    public Page<RepairOrder> searchOrder(String orderId, String serialNumber,String employeeId, int page, String sort) {
        RepairOrder exampleOrder = new RepairOrder();
        if(!orderId.isEmpty()){
            exampleOrder.setOrderId(Long.parseLong(orderId));
        }
        if(!employeeId.isEmpty()){
            Employee employee = new Employee();
            employee.setEmployeeId(Long.parseLong(employeeId));
            exampleOrder.setEmployees(employee);
        }
        if(!serialNumber.isEmpty()){
            Devices exampleDevice = new Devices();
            exampleDevice.setSerialNumber(serialNumber);
            exampleOrder.setDevices(exampleDevice);
        }

        Example<RepairOrder> example = Example.of(exampleOrder, SEARCH_CONDITIONS_MATCH_ALL);
        return ordersRepo.findAll(example, PageRequest.of(page - 1, pageSize));
    }
    private String parseThymeleafTemplate(Long orderId, WebContext context) {
        RepairOrderDTO extendedRepairOrders = this.getExtendedRepairOrders(orderId);

        context.setVariable("client", clientsService.getClientByDeviceId(extendedRepairOrders.getDevices().getDeviceId()));
        context.setVariable("order", extendedRepairOrders);

        return templateEngine.process("PdfTemplate", context);
    }
    public ByteArrayOutputStream generatePdf(Long orderId, WebContext context) throws DocumentException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String html = this.parseThymeleafTemplate(orderId, context);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);
        return outputStream;
    }
}
