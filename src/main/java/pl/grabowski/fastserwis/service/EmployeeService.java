package pl.grabowski.fastserwis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.grabowski.fastserwis.model.Employees;
import pl.grabowski.fastserwis.repository.EmployeeRepo;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    public List<Employees> getAllEmployees() {
        return StreamSupport
                .stream(employeeRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public List<Employees> searchEmployeesByLastName(String lastName) {
        return employeeRepo.getAllByLastNameIsLike(lastName);
    }
}
