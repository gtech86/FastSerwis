package pl.grabowski.fastserwis.service;


import pl.grabowski.fastserwis.model.Employees;

import java.util.List;

public interface EmployeeService {
    List<Employees> getAllEmployees();
    List<Employees> searchEmployeesByLastName(String lastName);
}
