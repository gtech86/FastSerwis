package pl.grabowski.fastserwis.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.grabowski.fastserwis.model.Client;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepo extends PagingAndSortingRepository<Client, Float> {
    Page<Client> findAll(Pageable pageable);
    Page<Client> findAll(Example<Client> example, Pageable pageable);
    Client getClientsByPhone(String phone);
    Optional<Client> getClientByClientId(Long clientId);
    Client getClientsByMail(String mail);

    List<Client> getClientsByFirstNameAndLastNameAndMailAndPhone(String firstName, String lastName, String mail, String phone);
}
