package pl.grabowski.fastserwis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.grabowski.fastserwis.dto.EmployeeCreateRequest;
import pl.grabowski.fastserwis.dto.EmployeeResponse;
import pl.grabowski.fastserwis.dto.EmployeeUpdateRequest;
import pl.grabowski.fastserwis.dto.SimpleEmployeeDTO;
import pl.grabowski.fastserwis.model.Client;
import pl.grabowski.fastserwis.model.Employee;
import pl.grabowski.fastserwis.model.Roles;
import pl.grabowski.fastserwis.repository.EmployeeRepo;
import pl.grabowski.fastserwis.repository.RolesRepo;
import pl.grabowski.fastserwis.service.mapper.EmployeeMapper;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService implements UserDetailsService {
    private final EmployeeRepo employeeRepo;
    private final EmployeeMapper employeeMapper;
    private final RolesRepo rolesRepo;
    private final BCryptPasswordEncoder passwordEncoder;

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

    public EmployeeCreateRequest searchEmployeesById(Long employeeId) {
        Employee employee = employeeRepo.findByEmployeeId(employeeId);
        EmployeeCreateRequest response = employeeMapper.toCreateEmployeeRequest(employee);
        response.setIsAdmin(employee.getRoles().getRoleName().equals("ADMIN"));
        return response;
    }

    public List<Employee> searchEmployeesByLastName(String lastName) {
        return employeeRepo.getAllByLastNameIsLike(lastName);
    }

    public void blockEmployee(Long employeeId) {
        Employee employeeDb = employeeRepo.findByEmployeeId(employeeId);
        employeeDb.setIsBlocked(true);
    }

    public Optional<Employee> searchEmployeesByUsername(String username) {
        return employeeRepo.findByUsername(username);
    }

    public void updateEmployee(EmployeeUpdateRequest updateRequest){
        Employee updateEntity = employeeMapper.toUpdateEntity(updateRequest);
        Employee employeeToUpdate = employeeRepo.findByEmployeeId(updateRequest.getEmployeeId());
        employeeToUpdate.update(updateEntity);
        if(updateRequest.getIsAdmin()){
            employeeToUpdate.setRoles(rolesRepo.findByRoleName("ADMIN"));
        }
        else{
            employeeToUpdate.setRoles(rolesRepo.findByRoleName("USER"));
        }

    }

    public List<SimpleEmployeeDTO> getAllSimpleEmployee(){
        return employeeMapper.toDto(employeeRepo.findAll());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> employee = employeeRepo.findByUsername(username);
        if (!employee.isPresent()){
            throw new UsernameNotFoundException("Użytkownika nie znaleziono!");
        }
        if(employee.isPresent()){
            if (employee.get().getIsBlocked()){
                throw new LockedException("Użytkownik jest zablokowany");
            }
        }
        return new User(
                employee.get().getUsername(),
                employee.get().getPassword(),
                Collections
                        .singletonList(
                                new SimpleGrantedAuthority(
                                        employee
                                                .get().getRoles()
                                                .getRoleName()
                                )
                        )
        );
    }

    public EmployeeResponse createUser(EmployeeCreateRequest employeeCreateRequest){
        Employee createEntity = employeeMapper.toCreateEntity(employeeCreateRequest);
        createEntity.setIsBlocked(false);
        createEntity.setPassword(passwordEncoder.encode(employeeCreateRequest.getPassword()));
        if(employeeCreateRequest.getIsAdmin()){
            createEntity.setRoles(rolesRepo.findByRoleName("ADMIN"));
        }
        else{
            createEntity.setRoles(rolesRepo.findByRoleName("USER"));
        }
        return employeeMapper.toCreateResponse(employeeRepo.save(createEntity));
    }

}
