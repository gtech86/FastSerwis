package pl.grabowski.fastserwis.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.grabowski.fastserwis.dto.UpdateClientRequest;
import pl.grabowski.fastserwis.model.Client;
import pl.grabowski.fastserwis.repository.ClientRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientsService {

    private final ClientRepo clientRepo;
    private final ModelMapper modelMapper;
    private static final int pageSize = 10;

    public Optional<Client> getClientById(Long clientId){
        return clientRepo.getClientByClientId(clientId);
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

    public Optional<Client> updateClient(Long clientId, UpdateClientRequest updatedClient){
        var clientToUpdate = clientRepo.getClientByClientId(clientId);
        if(clientToUpdate.isPresent()){
            modelMapper.map(updatedClient, clientToUpdate.get());
            return Optional.of(clientRepo.save(clientToUpdate.get()));
        }
        else return Optional.empty();

    }

    public List<Client> getClientBy(String firstName, String lastName, String mail, String phone) {
        return clientRepo.getClientsByFirstNameOrLastNameOrMailOrPhone(firstName, lastName, mail, phone);
    }

    public Page<Client> getClients(int page, String sort) {
        return clientRepo.findAll(PageRequest.of(page-1, pageSize, Sort.by(sort)));
    }
}
