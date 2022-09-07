package pl.grabowski.fastserwis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.grabowski.fastserwis.model.Clients;

@Repository
public interface ClientsRepo extends CrudRepository<Clients, Float> {
}
