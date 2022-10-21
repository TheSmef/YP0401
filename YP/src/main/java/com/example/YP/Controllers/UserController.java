package com.example.YP.Controllers;

import com.example.YP.Models.Role;
import com.example.YP.Models.User;
import com.example.YP.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String userView(Model model){
        model.addAttribute("userList", userRepository.findAll());
        return "user/user-view";
    }

    @GetMapping("/edit/{id}")
    public String userEditView(Model model, @PathVariable(name="id") Long id){
        model.addAttribute("roleList", Role.values());
        model.addAttribute("userOne", userRepository.findById(id).orElseThrow());
        return "user/user-edit";
    }

    @PostMapping("/edit/{id}")
    public String userEdit(Model model, @PathVariable(name="id") Long id, @RequestParam String username, @RequestParam(name = "roles[]") String[] roles){
        User user = userRepository.findById(id).orElseThrow();
        user.setUsername(username);
        user.getRoles().clear();
        for(String role : roles){
            user.getRoles().add(Role.valueOf(role));
        }
        userRepository.save(user);
        return "redirect:/user/";
    }

}
