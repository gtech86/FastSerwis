package pl.grabowski.fastserwis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.grabowski.fastserwis.model.Employees;

import java.util.List;

@Repository
public interface EmployeeRepo extends CrudRepository<Employees,Long> {
    List<Employees> getAllByLastNameIsLike(String lastName);
}
