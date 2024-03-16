package pl.grabowski.fastserwis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.grabowski.fastserwis.model.Roles;

@Repository
public interface RolesRepo extends CrudRepository<Roles, Integer> {
    Roles findByRoleName(String roleName);
    Roles findByRoleId(Integer roleId);
}
