package pl.fastserwis.fastserwis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.fastserwis.fastserwis.model.Clients;

@Repository
public interface ClientsRepo extends CrudRepository<Clients, Float> {
}
