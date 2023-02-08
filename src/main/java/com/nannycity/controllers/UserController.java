package com.nannycity;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/login")
    public String showLoginPage(){

        return "login";
    }
    @GetMapping("/nannyRegistration")
    public String showNannyRegistrationPage(){

        return "nannyRegistration";
    }
}
