package pl.grabowski.fastserwis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.grabowski.fastserwis.service.RepairOrdersService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class MainController {

    private final RepairOrdersService repairOrdersService;

    @GetMapping
    String mainBoard(Model model){
        model.addAttribute("orders", repairOrdersService.getSimpleRepairOrders());
        return "mainBoard";
    }
}
