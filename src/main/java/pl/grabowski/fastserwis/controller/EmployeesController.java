package pl.grabowski.fastserwis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.grabowski.fastserwis.dto.EmployeeCreateRequest;
import pl.grabowski.fastserwis.dto.EmployeeResponse;
import pl.grabowski.fastserwis.dto.EmployeeUpdateRequest;
import pl.grabowski.fastserwis.dto.device.DeviceDTO;
import pl.grabowski.fastserwis.dto.device.DeviceUpdateDTO;
import pl.grabowski.fastserwis.model.Employee;
import pl.grabowski.fastserwis.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeesController {

    private final EmployeeService employeeService;

    @GetMapping("/find")
    String findEmployee(Model model,
                        @AuthenticationPrincipal UserDetails currentUser,
                  @RequestParam(required = false) String firstName,
                  @RequestParam(required = false)  String  lastName,
                  @RequestParam(required = false)  String  mail,
                  @RequestParam(required = false)  String  phone,
                  @RequestParam(defaultValue = "1") int page,
                  @RequestParam(defaultValue = "employeeId") String sorting){
        Page<Employee> pageEmployees = employeeService.searchEmployee(firstName, lastName, mail, phone, page, sorting);

        List<Employee> employees = pageEmployees.stream().collect(Collectors.toList());

        model.addAttribute("employees", employees);
        model.addAttribute("currentUsername", currentUser.getUsername());
        model.addAttribute("totalPages", pageEmployees.getTotalPages());
        model.addAttribute("currentPage", page);
        return "employees";
    }

    @GetMapping("/add")
    public String addEmployee(
            Model model){
        model.addAttribute("employeeDTO", new EmployeeCreateRequest());
        return "/employee/CreateEmployee";

    }

    @PostMapping("/add")
    public String addEmployee(EmployeeCreateRequest employeeCreateRequest, Model model, RedirectAttributes attributes) {
        if (employeeService.searchEmployeesByUsername(employeeCreateRequest.getUsername()).isPresent()) {
            model.addAttribute("usernameExist", "Istnieje już użytkownik o takim loginie!");
            model.addAttribute("employeeDTO", employeeCreateRequest);
            return "/employee/CreateEmployee";
        }
        if(!employeeCreateRequest.getPassword().equals(employeeCreateRequest.getPassword2()) & !employeeCreateRequest.getPassword().isEmpty()){
            model.addAttribute("passNotEquals", "Hasła nie są takie same");
            model.addAttribute("employeeDTO", employeeCreateRequest);
            return "/employee/CreateEmployee";

        }

        EmployeeResponse user = employeeService.createUser(employeeCreateRequest);
        attributes.addFlashAttribute("userAdded", true);
        return "redirect:/employees/"+user.getEmployeeId();


    }

    @GetMapping("/edit")
    public String editEmployee(
            @RequestParam String employeeId,

            Model model){
        model.addAttribute("employeeDTO", employeeService.searchEmployeesById(Long.valueOf(employeeId)));
        return "/employee/UpdateEmployee";

    }

    @PostMapping("/edit")
    public String updateEmployee(@Valid EmployeeUpdateRequest employeeUpdateRequest, @RequestParam String employeeId,
                                 RedirectAttributes attributes) {
        employeeUpdateRequest.setEmployeeId(Long.valueOf(employeeId));
        employeeService.updateEmployee(employeeUpdateRequest);
        attributes.addFlashAttribute("userAdded", true);
        return "redirect:/employees/"+employeeId;
    }

    @GetMapping("/block/{employeeId}")
    public String getBlockEmployeeById(
                                  @PathVariable String employeeId) {
        employeeService.blockEmployee(Long.valueOf(employeeId));
        return "redirect:/employees/find?page=1";
    }

    @GetMapping("/{employeeId}")
    public String getEmployeeById(Model model,
                                @PathVariable String employeeId) {
        EmployeeCreateRequest employeeResponse = employeeService.searchEmployeesById(Long.valueOf(employeeId));
        model.addAttribute("employee", employeeResponse);
        return "/employee/Employee";
    }

}
