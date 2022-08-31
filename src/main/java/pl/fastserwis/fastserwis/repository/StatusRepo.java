package pl.fastserwis.fastserwis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.fastserwis.fastserwis.model.Status;

@Repository
public interface StatusRepo extends CrudRepository<Status, Integer> {
}
