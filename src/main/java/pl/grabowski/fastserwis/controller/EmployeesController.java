package pl.grabowski.fastserwis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import pl.grabowski.fastserwis.service.EmployeeService;


@Controller
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeesController {

    private final EmployeeService employeeService;

    @GetMapping
    String getAll(Model model){
        model.addAttribute("allEmployees", employeeService.getAllEmployees());
        return "employees";
    }
}
