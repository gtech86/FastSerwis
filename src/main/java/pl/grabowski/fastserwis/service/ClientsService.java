package pl.grabowski.fastserwis.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.grabowski.fastserwis.dto.client.ClientDTO;
import pl.grabowski.fastserwis.dto.client.UpdateClientRequest;
import pl.grabowski.fastserwis.model.Client;
import pl.grabowski.fastserwis.repository.ClientRepo;
import pl.grabowski.fastserwis.service.mapper.ClientMapper;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientsService {

    private final ClientRepo clientRepo;
    private final ClientMapper clientMapper;
    private final ModelMapper modelMapper;
    @Value("${page.size}")
    private int pageSize;

    private static final ExampleMatcher SEARCH_CONDITIONS_MATCH_ALL = ExampleMatcher
            .matching()
            .withMatcher("firstName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("lastName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("mail", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("phone", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withIgnorePaths("clientId", "streetNumber", "flatNumber", "street", "postalCode", "city");

    public Optional<Client> getClientById(Long clientId){
        return clientRepo.getClientByClientId(clientId);
    }

    public ClientDTO getClientByDeviceId(Long deviceId){
        return clientMapper.toDto(clientRepo.findClientByDeviceId(deviceId));
    }
    public ClientDTO getClientDtoById(Long clientId){
        Optional<Client> client = clientRepo.getClientByClientId(clientId);
        return clientMapper.toDto(client.orElseThrow());
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
    @Transactional
    public Page<Client> searchClient(String firstName, String lastName, String mail, String phone, int page, String sort) {
        Client client = Client.builder()
                .firstName(firstName)
                .lastName(lastName)
                .mail(mail)
                .phone(phone)
                .build();

        Example<Client> example = Example.of(client, SEARCH_CONDITIONS_MATCH_ALL);

        Page<Client> clients = clientRepo.findAll(example, PageRequest.of(page-1, pageSize, Sort.by(sort)));

        return clients;
    }

    public Page<Client> getClients(int page, String sort) {
        return clientRepo.findAll(PageRequest.of(page-1, pageSize, Sort.by(sort)));
    }
}
