package pl.grabowski.fastserwis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.grabowski.fastserwis.model.Clients;
import pl.grabowski.fastserwis.repository.ClientsRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientsService {

    private final ClientsRepo clientsRepo;

    public Clients getClientById(String clientId){
        return clientsRepo.getClientsByPhone(clientId);
    }

    public List<Clients> getClientByLastName(String lastName){
        return null;
    }

    public Clients getClientByPhoneNumber(String phoneNumber){
        return null;
    }
    public Clients getClientByMail(String mail){
        return null;
    }

}
