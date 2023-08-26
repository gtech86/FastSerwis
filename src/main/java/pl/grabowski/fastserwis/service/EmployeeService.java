package pl.grabowski.fastserwis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.grabowski.fastserwis.model.Client;
import pl.grabowski.fastserwis.model.Employee;
import pl.grabowski.fastserwis.repository.EmployeeRepo;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    @Value("${page.size}")
    private int pageSize;
    private static final ExampleMatcher SEARCH_CONDITIONS_MATCH_ALL = ExampleMatcher
            .matching()
            .withMatcher("firstName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("lastName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("mail", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("phone", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withIgnorePaths("employeeId", "password");

    @Transactional
    public Page<Employee> searchEmployee(String firstName, String lastName, String mail, String phone, int page, String sort) {
        Employee employee = Employee.builder()
                .firstName(firstName)
                .lastName(lastName)
                .mail(mail)
                .phone(phone)
                .build();

        Example<Employee> example = Example.of(employee, SEARCH_CONDITIONS_MATCH_ALL);

        Page<Employee> employees = employeeRepo.findAll(example, PageRequest.of(page-1, pageSize, Sort.by(sort)));

        return employees;
    }

    public List<Employee> searchEmployeesByLastName(String lastName) {
        return employeeRepo.getAllByLastNameIsLike(lastName);
    }
}
