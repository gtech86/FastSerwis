package pl.grabowski.fastserwis.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.grabowski.fastserwis.dto.CreateClientRequest;
import pl.grabowski.fastserwis.dto.UpdateClientRequest;
import pl.grabowski.fastserwis.model.Client;
import pl.grabowski.fastserwis.service.ClientsService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientsController {
    private final ClientsService clientsService;
    private final ModelMapper mapper;

    @GetMapping("/{clientId}")
    public String getClientById(Model model,
                                @PathVariable Long clientId) {
        var client = clientsService.getClientById(clientId);
        if(client.isPresent()){
            model.addAttribute("client", client.get());
            return "/client/client";
        }
        model.addAttribute("errorMessage", "Brak klienta o podanym numerze w systemie!! :(");
        return "errorPage";
    }

    @GetMapping("/find")
    public String getClientByPersonalData(
            Model model,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false)  String  lastName,
            @RequestParam(required = false)  String  mail,
            @RequestParam(required = false)  String  phone,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "clientId") String sorting)
    {
        Page<Client> pageClients = clientsService.searchClient(firstName, lastName, mail, phone,page, sorting);
        List<Client> clients = pageClients
                .stream()
                .collect(Collectors.toList());
        model.addAttribute("clients", clients);
        model.addAttribute("totalPages", pageClients.getTotalPages());
        model.addAttribute("currentPage", page);
        return "/client/clients";
    }
    @GetMapping("/add")
    public String newClient(Model model){
        model.addAttribute("client", new CreateClientRequest());
        return "/client/CreateClient";
    }

    @PostMapping("/add")
    public String addNewClient(@Valid CreateClientRequest clientParameter, RedirectAttributes attributes) {
        Client newClient = mapper.map(clientParameter, Client.class);
        var addedClient = clientsService.addNewClient(newClient);
        attributes.addFlashAttribute("newUser", addedClient);
        return "redirect:/clients/"+addedClient.getClientId();
    }

    @GetMapping("/edit/{clientId}")
    public String editClient(
            @PathVariable Long clientId,
            Model model){
        var client =clientsService.getClientById(clientId);
        if (client.isPresent()){
            model.addAttribute("client", client.get());
            model.addAttribute("clientId", clientId);
            return "/client/updateClient";
        }
        model.addAttribute("errorMessage", "Brak klienta o podanym numerze w systemie!! :(");
        return "errorPage";
    }

    @PostMapping("/edit/{clientId}")
    public String editClient(@Valid UpdateClientRequest updateClientRequest, @PathVariable Long clientId) {
        clientsService.updateClient(clientId, updateClientRequest);
        return "redirect:/clients/"+clientId;
    }


}
