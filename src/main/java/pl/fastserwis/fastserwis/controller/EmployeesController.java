package pl.fastserwis.fastserwis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pl.fastserwis.fastserwis.service.EmployeeService;


@Controller
@RequiredArgsConstructor
public class EmployeesController {

    private final EmployeeService employeeService;

    @GetMapping("/employees")
    String getAll(Model model){
        model.addAttribute("allEmployees", employeeService.getAllEmployees());
        return "employees";
    }
}
