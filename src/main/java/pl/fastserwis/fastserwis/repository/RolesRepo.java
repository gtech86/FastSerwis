package pl.fastserwis.fastserwis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.fastserwis.fastserwis.model.Roles;

@Repository
public interface RolesRepo extends CrudRepository<Roles, Integer> {
}
