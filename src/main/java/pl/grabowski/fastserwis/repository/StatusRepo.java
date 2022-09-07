package pl.grabowski.fastserwis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.grabowski.fastserwis.model.Status;

@Repository
public interface StatusRepo extends CrudRepository<Status, Integer> {
}
