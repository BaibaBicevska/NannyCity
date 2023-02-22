package com.nannycity.controllers;

import com.nannycity.entities.NannyUser;
import com.nannycity.entities.ParentUser;
import com.nannycity.repositories.NannyRepository;
import com.nannycity.repositories.ParentRepository;
import com.nannycity.services.ParentService;
import com.nannycity.services.UserService;
import jakarta.persistence.Column;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
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

}