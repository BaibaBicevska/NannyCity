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

    @GetMapping("/login")
    public String showNannieLogin(Model model) {
        return "login";
    }

    @GetMapping("/nannyRegistration")
    public String showNannyRegistrationPage(){
        return "nannyRegistration";
    }

    @Autowired
    private NannyRepository nannyRepository;

    @GetMapping("allNannies")
    public String showNannie(Model model) {
        Iterable<NannyUser> nannies = nannyRepository.findAll();
        model.addAttribute("nannies", nannies);
        return "allNannies";
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

    @GetMapping("nanniesAdmin")
    public String showNanniesAdmin(Model model) {
        Iterable<NannyUser> nannies = nannyRepository.findAll();
        model.addAttribute("nannies", nannies);
        return "nanniesAdmin";
    }

  @GetMapping("/nannyProfile")
    public String showNannyProfile () {
        return "nannyProfile";
    }

}

