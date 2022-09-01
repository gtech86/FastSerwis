package pl.fastserwis.fastserwis.service;


import pl.fastserwis.fastserwis.model.Employees;

import java.util.List;

public interface EmployeeService {
    List<Employees> getAllEmployees();
    List<Employees> searchEmployeesByLastName(String lastName);
}
