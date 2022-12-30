package pl.grabowski.fastserwis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.grabowski.fastserwis.repository.RepairOrdersRepo;
import pl.grabowski.fastserwis.service.RepairOrdersService;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {
    private final RepairOrdersService ordersService;
    private final RepairOrdersRepo ordersRepo;

    @GetMapping("{orderId}")
    public String getOrderById(Model model, @PathVariable(required = true) Long orderId){
        var extendedOrder = ordersService.getExtendedRepairOrders(orderId);
        if(extendedOrder.isPresent()){
            model.addAttribute("order", extendedOrder.get());
            return "/order/searchOrderId";
        }
        model.addAttribute("errorMessage", "Brak zlecenia o podanym numerze w systemie!! :(");
        return "errorPage";
    }

    public String getOrderByClientId(@PathVariable(required = true) Long clientId){
        return "";
    }

    @GetMapping
    public String getOrderByStatus(Model model, @RequestParam String ordersStatus){
        var orders = ordersRepo.getRepairOrdersByStatus(ordersStatus);
        model.addAttribute("orders", orders);
        return "/order/searchOrder";

    }
}
