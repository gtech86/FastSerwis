package pl.fastserwis.fastserwis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.fastserwis.fastserwis.model.Employees;

@Repository
public interface EmployeeRepo extends CrudRepository<Employees,Long> {
}
