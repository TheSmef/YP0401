package com.example.YP.Controllers;

import com.example.YP.Models.Post;
import com.example.YP.Models.Role;
import com.example.YP.Models.User;
import com.example.YP.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public String userView(Model model){
        model.addAttribute("userList", userRepository.findAll());
        return "user/user-view";
    }

    @GetMapping("/edit/{id}")
    public String userEditView(Model model, @PathVariable(name="id") Long id, User user){
        model.addAttribute("roleList", Role.values());
        model.addAttribute("userOne", userRepository.findById(id).orElseThrow());
        return "user/user-edit";
    }

    @PostMapping("/edit/{id}")
    public String userEdit(Model model, @PathVariable(name="id") Long id, @Valid User user, BindingResult bindingResult, @RequestParam(name = "roles[]") String[] roles){
        User userTarget = userRepository.findById(id).orElseThrow();
        if (bindingResult.hasFieldErrors("username") || bindingResult.hasFieldErrors("email")){
            model.addAttribute("userOne", user);
            model.addAttribute("roleList", Role.values());
            return "user/user-edit";
        }
        if (userRepository.findByUsername(user.getUsername()) != null || userRepository.findByEmail(user.getEmail()) != null){
            model.addAttribute("error", "Такой пользователь уже существует");
            model.addAttribute("userOne", user);
            model.addAttribute("roleList", Role.values());
            return "user/user-edit";
        }
        userTarget.getRoles().clear();
        for(String role : roles){
            userTarget.getRoles().add(Role.valueOf(role));
        }
        userTarget.setEmail(user.getEmail());
        userTarget.setUsername(user.getUsername());

        userRepository.save(userTarget);
        return "redirect:/user/";
    }
    @GetMapping("/delete/{id}")
    public String userDelete(@PathVariable(name="id") Long id){
        userRepository.deleteById(id);
        return "redirect:/user/";
    }

    @GetMapping("/filter-direct")
    public String Filter(@RequestParam String searchName,
                             Model model){
        List<User> users = userRepository.findUsersByUsername(searchName);
        model.addAttribute("userList", users);
        return "user/user-view";
    }

    @GetMapping("/filter-contains")
    public String FilterContains(@RequestParam String searchName,
                                     Model model){
        List<User> users = userRepository.findUsersByUsernameContaining(searchName);
        model.addAttribute("userList", users);
        return "user/user-view";
    }
}
