package pl.grabowski.fastserwis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.grabowski.fastserwis.dto.CreateClientRequest;
import pl.grabowski.fastserwis.service.ClientsService;
@Controller
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientsController {
    private final ClientsService clientsService;

    @GetMapping("{clientId}")
    public String getClientById(Model model, @PathVariable(required = true) String clientId){
        model.addAttribute("client", clientsService.getClientById(clientId));
        return "searchClient";
    }
    @GetMapping("/add")
    public String newClient(Model model){
        return "CreateClient";
    }

    @PostMapping("/add")
    public String addNewClient(@ModelAttribute("CreateClientRequest") CreateClientRequest createClientRequest) {
        return "CreateClient";
    }


}
