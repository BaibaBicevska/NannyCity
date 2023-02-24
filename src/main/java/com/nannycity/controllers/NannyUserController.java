package com.nannycity.controllers;


import com.nannycity.entities.NannyUser;
import com.nannycity.repositories.NannyRepository;
import com.nannycity.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class NannyUserController {
    private final UserService userService;

    @Autowired
    public NannyUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showNannieLogin(
            @RequestParam(name = "message", required = false) String message,
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

    @Autowired
    private NannyRepository nannyRepository;

    @GetMapping("allNannies")
    public String showNannie(Model model) {
        Iterable<NannyUser> nannies = nannyRepository.findAll();
        model.addAttribute("nannies", nannies);
        return "allNannies";
    }

    @GetMapping("/nannyRegistration")
    public String showNannyRegistrationPage(NannyUser nannyUser) {
        return "nannyRegistration";
    }

    /*@PostMapping("/nannyRegistration")
    public String addNanny(
            @RequestParam String userName,
            @RequestParam String userFullName,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String birthDay,
            @RequestParam String phone,
            @RequestParam String location,
            @RequestParam String address,
            @RequestParam String[] hours,
            @RequestParam String[] skills,
            @RequestParam String education,
            @RequestParam String experience,
            @RequestParam String[] language,
            @RequestParam String description,
            Model model) {
        NannyUser nannyUser = new NannyUser(userName, userFullName, email, password,
                birthDay, phone, location, address, String.join(",", hours), String.join("," , skills),
                education, experience, String.join(",", language), description);
        nannyRepository.save(nannyUser);
        return "login";
    }*/

    /*@PostMapping("/nannyRegistration")
    public String addNanny(@Valid NannyUser nannyUser, Errors errors, Model model) {
        if (null != errors && errors.getErrorCount() > 0) {
            System.out.println("ndkfdjfjdk");
            return "nannyRegistration";
        } else {
            nannyRepository.save(nannyUser);
            return "login";
        }
    }*/

    @PostMapping("/nannyRegistration")
    public String addNanny(@Valid NannyUser nannyUser, Model model) {
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

    @GetMapping("nannyProfile/{id}/edit")
    public String editNannyProfile(@PathVariable(value = "id") long id, Model model) {
        Optional<NannyUser> nannyUser = nannyRepository.findById(id);
        ArrayList<NannyUser> result = new ArrayList<>();
        nannyUser.ifPresent(result::add);
        model.addAttribute("nannyUser", result);
        return "nannyProfileEdit";
    }

    @PostMapping("nannyProfile/{id}/edit")
    public String nannyProfileUpdate(
            @PathVariable(value = "id") long id,
            @RequestParam String userName,
            @RequestParam String userFullName,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String birthDay,
            @RequestParam String phone,
            @RequestParam String location,
            @RequestParam String address,
            @RequestParam String[] hours,
            @RequestParam String[] skills,
            @RequestParam String education,
            @RequestParam String experience,
            @RequestParam String[] language,
            @RequestParam String description,
            Model model) {
        NannyUser nannyUser = nannyRepository.findById(id).orElseThrow();
        nannyUser.setUserName(userName);
        nannyUser.setUserFullName(userFullName);
        nannyUser.setEmail(email);
        nannyUser.setPassword(password);
        nannyUser.setBirthDay(birthDay);
        nannyUser.setPhone(phone);
        nannyUser.setLocation(location);
        nannyUser.setAddress(address);
        nannyUser.setHours(String.join(",", hours));
        nannyUser.setSkills(String.join(",", skills));
        nannyUser.setEducation(education);
        nannyUser.setExperience(experience);
        nannyUser.setLanguage(String.join(",", language));
        nannyUser.setDescription(description);
        nannyRepository.save(nannyUser);
        return "redirect:/nannyProfile/{id}";
    }

    @PostMapping("nannyProfile/{id}/remove")
    public String nannyProfileDelete(
            @PathVariable(value = "id") long id,
            Model model) {
        NannyUser nannyUser = nannyRepository.findById(id).orElseThrow();
        nannyRepository.delete(nannyUser);
        return "redirect:/";
    }

    @GetMapping("/contacts")
    public String showContactsPage(Model model) {
        return "contacts";
    }

    @GetMapping("/about_us")
    public String showAboutUsPage(Model model) {
        return "about_us";
    }
}

