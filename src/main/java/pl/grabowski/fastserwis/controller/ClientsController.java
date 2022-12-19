package pl.grabowski.fastserwis.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.grabowski.fastserwis.dto.CreateClientRequest;
import pl.grabowski.fastserwis.model.Client;
import pl.grabowski.fastserwis.service.ClientsService;

import javax.validation.Valid;

@Controller
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientsController {
    private final ClientsService clientsService;
    private final ModelMapper mapper = new ModelMapper();

    @GetMapping("/{clientId}")
    public String getClientById(Model model, @PathVariable(required = true) Long clientId){
        model.addAttribute("client", clientsService.getClientById(clientId));
        return "client";
    }

    @GetMapping("/find")
    public String getClientByPersonalData(
            Model model,
            @RequestParam(required = false)  String firstName,
            @RequestParam(required = false)  String lastName,
            @RequestParam(required = false)  String mail,
            @RequestParam(required = false)  String phone)
    {
        model.addAttribute("clients", clientsService.getClientBy(firstName, lastName, mail, phone));
        return "clients";
    }
    @GetMapping("/add")
    public String newClient(Model model){
        model.addAttribute("client", new CreateClientRequest());
        return "CreateClient";
    }

    @PostMapping("/add")
    public String addNewClient(@Valid @ModelAttribute CreateClientRequest clientParameter) {
        Client newClient = mapper.map(clientParameter, Client.class);
        var addedClient = clientsService.addNewClient(newClient);
        System.out.println(clientParameter);
        return "redirect:/clients";
    }


}
