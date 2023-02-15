package com.nannycity.controllers;

import com.nannycity.entities.NannyUser;
import com.nannycity.entities.ParentUser;
import com.nannycity.repositories.NannyRepository;
import com.nannycity.repositories.ParentRepository;
import com.nannycity.services.UserService;
import jakarta.persistence.Column;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;

@Controller
public class ParentController {

    private UserService userService;


    @Autowired
    public ParentRepository parentRepository;

    @GetMapping("/parentLogin")
    public String showParentLogin(Model model) {
        return "parentLogin";
    }


    @GetMapping("/parentsRegistration")
    public String showParentRegistration() {
        return "parentsRegistration";
    }

    @GetMapping("/parentProfile")
    public String showParents(Model model) {
        Iterable<ParentUser> parents = parentRepository.findAll();
        model.addAttribute("parents", parents);
        return "parentProfile";
    }

    @PostMapping("/parentsRegistration")
    public String addParent(
            @RequestParam String userName,
            @RequestParam String userSecondName,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String phone,
            @RequestParam String location,
            @RequestParam String address,
            @RequestParam String hours,
            @RequestParam String language,
            @RequestParam String description,
            Model model) {
        ParentUser parentUser = new ParentUser(userName, userSecondName,
                email, password, phone, location,
                address, hours, language, description);
        parentRepository.save(parentUser);
        return "parentLogin";
    }

}