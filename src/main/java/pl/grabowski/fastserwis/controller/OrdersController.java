package pl.grabowski.fastserwis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.grabowski.fastserwis.service.RepairOrdersService;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {
    private final RepairOrdersService ordersService;

    @GetMapping("{orderId}")
    public String getClientById(Model model, @PathVariable(required = true) Long orderId){
        if(ordersService.getOrderById(orderId).isPresent()){

            return "searchOrderId";
        }
        model.addAttribute("errorMessage", "Brak zlecenia o podanym ID w systemie!! :(");
        return "errorPage";
    }
}
