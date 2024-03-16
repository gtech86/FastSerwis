package pl.grabowski.fastserwis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.grabowski.fastserwis.service.RepairOrdersService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if(error!=null){
            model.addAttribute("error", "Nieprawidłowa nazwa użytkownika lub hasło.");
            return "Login";
        }
        return "Login";
    }
}
