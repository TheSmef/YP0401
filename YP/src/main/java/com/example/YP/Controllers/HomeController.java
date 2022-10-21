package com.example.YP.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@PreAuthorize("hasAnyAuthority('USER')")
public class HomeController {
    @GetMapping("/")
    public String index(){
        return "Index";
    }
    @GetMapping("/main")
    public String main(@RequestParam(required = false) int first,
                       @RequestParam(required = false) int second,
                       @RequestParam(required = false) String op,
                       Model model){
        model.addAttribute("first", first);
        model.addAttribute("second", second);
        model.addAttribute("op", op);
        int result = 0;
        switch (op){
            case "+":{
                result = first+second;
                break;
            }
            case "-":{
                result = first-second;
                break;
            }
            case "*":{
                result = first*second;
                break;
            }
            case "/":{
                result = first/second;
                break;
            }
        }
        model.addAttribute("result", result);
        return "Home";
    }
    @PostMapping("/main")
    public String mainPost(@RequestParam(required = false) int first,
                       @RequestParam(required = false) int second,
                       @RequestParam(required = false) String op,
                       Model model){
        model.addAttribute("first", first);
        model.addAttribute("second", second);
        model.addAttribute("op", op);
        int result = 0;
        switch (op){
            case "+":{
                result = first+second;
                break;
            }
            case "-":{
                result = first-second;
                break;
            }
            case "*":{
                result = first*second;
                break;
            }
            case "/":{
                result = first/second;
                break;
            }
        }
        model.addAttribute("result", result);
        return "Home";
    }
}
