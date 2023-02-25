package com.nannycity.controllers;

import com.nannycity.entities.ParentUser;
import com.nannycity.repositories.ParentRepository;
import com.nannycity.services.ParentService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ParentController {


    private final ParentService parentService;

    @Autowired
    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

    @Autowired
    private ParentRepository parentRepository;

    @GetMapping("/parentLogin")
    public String showParentLogin(

            @RequestParam(name="message", required = false) String message,
            Model model) {
        model.addAttribute("message", message);
        return "parentLogin";
    }

    @PostMapping("/parentLogin")
    public String handleParentLogin(ParentUser parentUser, HttpServletResponse response) {

        try {
            ParentUser loggedInUser = parentService.verifyUserParent(parentUser);
            Cookie cookie = new Cookie("userId", loggedInUser.getId().toString());
            response.addCookie(cookie);
            response.addCookie(new Cookie("userIsLoggedIn", "true"));
            return "redirect:parentProfile/" + loggedInUser.getId();
        } catch (Exception e) {
            return "redirect:parentLogin?message=login_failed&error=" + e.getMessage();
        }
    }



    @GetMapping("/parentsRegistration")
    public String showParentRegistration(Model model) {
        model.addAttribute("all", parentRepository.findAll());
        return "parentsRegistration";
    }

    @GetMapping("/parentProfile/{id}")
    public String showParents(@PathVariable(value = "id") long id, Model model) {
        Optional<ParentUser> parentUser = parentRepository.findById(id);
        ArrayList<ParentUser> result = new ArrayList<>();
        parentUser.ifPresent(result::add);
        model.addAttribute("parentUser", result);
        return "parentProfile";
    }


//    @PostMapping("/parentsRegistration")
//    public String addParent(
//            @RequestParam String userName,
//            @RequestParam String userFullName,
//            @RequestParam String email,
//            @RequestParam String password,
//            @RequestParam String phone,
//            @RequestParam String location,
//            @RequestParam String address,
//            @RequestParam String description,
//            @RequestParam String[] hours,
//            @RequestParam String[] language,
//            Model model) {
//        ParentUser parentUser = new ParentUser(userName, userFullName,
//                email, password, phone, location,
//                address, description, String.join(",", hours), String.join(",", language));
//        ParentUser a = parentRepository.save(parentUser);
//        System.out.println(a);
//        return "parentLogin";
//    }

    @PostMapping("/parentsRegistration")
    public String addParent(@Valid ParentUser parentUser, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println(result.hasErrors());
            return "/parentsRegistration";
        }
        parentRepository.save(parentUser);
        return "parentLogin";
    }


    @GetMapping("/parentsRegistration/{id}")
    public String parentProfileDetails(@PathVariable(value = "id") long id, @NotNull Model model) {
        Optional<ParentUser> parentUser = parentRepository.findById(id);
        ArrayList<ParentUser> result = new ArrayList<>();
        parentUser.ifPresent(result::add);
        model.addAttribute("parentUser", result);
        return "parentProfile";
    }

    @GetMapping("parentProfile/{id}/edit")
    public String editParentProfile(@PathVariable(value = "id") long id, Model model) {
        Optional<ParentUser> parentUser = parentRepository.findById(id);
        ArrayList<ParentUser> result = new ArrayList<>();
        parentUser.ifPresent(result::add);
        model.addAttribute("parentUser", result);
        return "parentProfileEdit";
    }

    @PostMapping("parentProfile/{id}/edit")
    public String nannyProfileUpdate(
            @PathVariable(value = "id") long id,
            @RequestParam String userName,
            @RequestParam String userFullName,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String phone,
            @RequestParam String location,
            @RequestParam String address,
            @RequestParam String[] hours,
            @RequestParam String[] language,
            @RequestParam String description,
            Model model) {
        ParentUser parentUser = parentRepository.findById(id).orElseThrow();
        parentUser.setUserName(userName);
        parentUser.setUserFullName(userFullName);
        parentUser.setEmail(email);
        parentUser.setPassword(password);
        parentUser.setPhone(phone);
        parentUser.setLocation(location);
        parentUser.setAddress(address);
        parentUser.setHours(String.join(",", hours));
        parentUser.setLanguage(String.join(",", language));
        parentUser.setDescription(description);
        parentRepository.save(parentUser);
        return "redirect:/parentProfile/{id}";
    }

}