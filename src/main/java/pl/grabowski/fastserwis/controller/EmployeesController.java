package pl.grabowski.fastserwis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.grabowski.fastserwis.model.Employee;
import pl.grabowski.fastserwis.service.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeesController {

    private final EmployeeService employeeService;

    @GetMapping
    String findEmployee(Model model,
                  @RequestParam(required = false) String firstName,
                  @RequestParam(required = false)  String  lastName,
                  @RequestParam(required = false)  String  mail,
                  @RequestParam(required = false)  String  phone,
                  @RequestParam(defaultValue = "1") int page,
                  @RequestParam(defaultValue = "employeeId") String sorting){
        Page<Employee> pageEmployees = employeeService.searchEmployee(firstName, lastName, mail, phone, page, sorting);

        List<Employee> employees = pageEmployees.stream().collect(Collectors.toList());

        model.addAttribute("employees", employees);
        model.addAttribute("totalPages", pageEmployees.getTotalPages());
        model.addAttribute("currentPage", page);
        return "employees";
    }
}
