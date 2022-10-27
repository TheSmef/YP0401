package com.example.YP.Controllers;

import com.example.YP.Models.Employee;
import com.example.YP.Models.Role;
import com.example.YP.Models.User;
import com.example.YP.Repository.EmployeeRepository;
import com.example.YP.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String regView(Employee employee, User user){
        return "registration";
    }
    @PostMapping("/registration")
    public String addUser(@Valid Employee employee,BindingResult bindingResultEm, @Valid User user, BindingResult bindingResultUser, Model model){
        if (bindingResultEm.hasErrors() || bindingResultUser.hasErrors()){
            model.addAttribute("user", user);
            model.addAttribute("employee", employee);
            return "registration";
        }
        if (userRepository.findByUsername(user.getUsername()) != null || userRepository.findByEmail(user.getEmail()) != null){
            model.addAttribute("error", "Такой пользователь уже существует");
            return "registration";
        }
        String pass = passwordEncoder.encode(user.getPassword());
        user.setActive(true);
        user.setPassword(pass);
        user.setEmployee(employee);
        user.setDateCreation(new Date());
        user.setRoles(Collections.singleton(Role.ADMIN));
        userRepository.save(user);
        return "redirect:login";
    }
}
