package pl.grabowski.fastserwis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.grabowski.fastserwis.model.Client;

import java.util.List;

@Repository
public interface ClientRepo extends CrudRepository<Client, Float> {
    Client getClientsByPhone(String phone);
    Client getClientsByClientId(Long clientId);
    Client getClientsByMail(String mail);

    List<Client> getClientsByFirstNameOrLastNameOrMailOrPhone(String firstName, String lastName, String mail, String phone);
}
