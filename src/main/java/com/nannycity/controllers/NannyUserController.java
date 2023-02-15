package com.nannycity.controllers;


import com.nannycity.entities.NannyUser;
import com.nannycity.repositories.NannyRepository;
import com.nannycity.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

import static javax.swing.text.html.HTML.Attribute.N;

@Controller
public class NannyUserController {

    @Autowired
    public NannyUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showNannieLogin(
            @RequestParam(name="message", required = false) String message,
            Model model) {
        model.addAttribute("message", message);
        return "login";
    }

    @PostMapping("/login")
    public String handleNannyLogin(NannyUser nannyUser, HttpServletResponse response) {
        try {
            NannyUser loggedInUser = userService.verifyUser(nannyUser);
            Cookie cookie = new Cookie("userId", loggedInUser.getId().toString());
            response.addCookie(cookie);
            response.addCookie(new Cookie("userIsLoggedIn", "true"));
            return "redirect:nannyProfile/" + loggedInUser.getId();
        } catch (Exception e) {
            return "redirect:login?message=login_failed&error=" + e.getMessage();
        }
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

    @GetMapping("/nannyProfile/{id}")
    public String nanniesProfileDetails(@PathVariable(value = "id") long id, Model model) {
        Optional<NannyUser> nannyUser = nannyRepository.findById(id);
        ArrayList<NannyUser> result = new ArrayList<>();
        nannyUser.ifPresent(result::add);
        model.addAttribute("nannyUser", result);
        return "nannyProfile";
    }

    /*@GetMapping("/nannyProfile/{id}")
    public String addNannie(@PathVariable(value = "id") long id, Model model) {
        Iterable<NannyUser> nannies = nannyRepository.findAll();
        model.addAttribute("nannies", nannies);
        return "nannyProfile";
    }*/

    /*@PostMapping("/nannyProfile/{id}")
    public String addNannieeProfile(@PathVariable(value = "id") long id, Model model) {
        return "nannyProfile";    }*/

    private final UserService userService;

}

