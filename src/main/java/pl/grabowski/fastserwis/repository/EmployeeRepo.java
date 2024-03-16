package pl.grabowski.fastserwis.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.grabowski.fastserwis.model.Client;
import pl.grabowski.fastserwis.model.Employee;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends PagingAndSortingRepository<Employee, Long> {
    Page<Employee> findAll(Example<Employee> example, Pageable pageable);
    Optional<Employee> findByUsername(String username);
    List<Employee> getAllByLastNameIsLike(String lastName);
    @Override
    List<Employee> findAll();
    Employee findByEmployeeId(Long id);
}
