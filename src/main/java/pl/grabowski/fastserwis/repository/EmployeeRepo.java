package pl.grabowski.fastserwis.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.grabowski.fastserwis.model.Client;
import pl.grabowski.fastserwis.model.Employee;

import java.util.List;

@Repository
public interface EmployeeRepo extends CrudRepository<Employee,Long> {
    Page<Employee> findAll(Example<Employee> example, Pageable pageable);
    List<Employee> getAllByLastNameIsLike(String lastName);
}
