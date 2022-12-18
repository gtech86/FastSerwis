package pl.grabowski.fastserwis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.grabowski.fastserwis.model.Client;
import pl.grabowski.fastserwis.repository.ClientRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientsService {

    private final ClientRepo clientRepo;

    public Client getClientById(Long clientId){
        return clientRepo.getClientsByClientId(clientId);
    }

    public List<Client> getClientByLastName(String lastName){
        return null;
    }

    public Client getClientByPhoneNumber(String phoneNumber){
        return clientRepo.getClientsByPhone(phoneNumber);
    }
    public Client getClientByMail(String mail){
        return clientRepo.getClientsByMail(mail);
    }

    public Client addNewClient(Client newClient){
        return clientRepo.save(newClient);
    }

    public List<Client> getClientBy(String firstName, String lastName, String mail, String phone) {
        return clientRepo.getClientsByFirstNameOrLastNameOrMailOrPhone(firstName, lastName, mail, phone);
    }
}
