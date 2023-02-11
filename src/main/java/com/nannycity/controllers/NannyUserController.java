package com.nannycity.controllers;


import com.nannycity.entities.NannyUser;
import com.nannycity.repositories.NannyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NannyUserController {
    @Autowired
    private NannyRepository nannyRepository;

    @GetMapping("nannyRegistration/add")
    public String showNannie(Model model) {
        Iterable<NannyUser> nannies = nannyRepository.findAll();
        model.addAttribute("nannies", nannies);
        return "redirect:/login";
    }

    /*@GetMapping("/allNannies")
    public String showAllNanniesPage(){

        return "allNannies";
    }*/

    @GetMapping("/login")
    public String showLoginPage(){

        return "login";
    }
    @GetMapping("/nannyRegistration")
    public String showNannyRegistrationPage(){

        return "nannyRegistration";
    }

    @GetMapping("/parentsRegistration")
    public String showParentsRegistrationPage(){

        return "parentsRegistration";
    }

    @PostMapping("/nannyRegistration")
    public String addNanny(
            @RequestParam String userName,
            @RequestParam String userSecondName,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String birthDay,
            @RequestParam String phone,
            @RequestParam String location,
            @RequestParam String address,
            @RequestParam String hours,
            @RequestParam String skills,
            @RequestParam String education,
            @RequestParam String experience,
            @RequestParam String language,
            @RequestParam String description,
            Model model) {
        NannyUser nannyUser = new NannyUser(userName, userSecondName, email, password,
                birthDay, phone, location, address, hours, skills, education,
                experience, language, description);
        nannyRepository.save(nannyUser);
        return "login";
    }


}

