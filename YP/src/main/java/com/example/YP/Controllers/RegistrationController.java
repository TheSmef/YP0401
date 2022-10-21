package com.example.YP.Controllers;

import com.example.YP.Models.Role;
import com.example.YP.Models.User;
import com.example.YP.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String regView(User user){
        return "registration";
    }
    @PostMapping("/registration")
    public String addUser(User user, Model model){
        if (userRepository.findByUsername(user.getUsername()) != null){
            model.addAttribute("error", "Такой польщзователь уже существует");
            return "registration";
        }
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return "redirect:login";
    }
}
