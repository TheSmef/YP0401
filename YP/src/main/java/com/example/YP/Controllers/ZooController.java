package com.example.YP.Controllers;

import com.example.YP.Models.Zoo;
import com.example.YP.Repository.ZooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/zoo")
@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
public class ZooController {
    @Autowired
    ZooRepository zooRepository;
    @GetMapping("")
    public String main(Model model){
        Iterable<Zoo> listZoo = zooRepository.findAll();
        model.addAttribute("listAnimal", listZoo);
        return "zoo/Index";
    }

    @GetMapping("/add")
    public String zooAddView(Model model, Zoo zoo){
        return "zoo/add";
    }
    @PostMapping("/add")
    public String zooAdd(
//                         @RequestParam String name,
//                         @RequestParam String description,
//                         @RequestParam Integer age,
//                         @RequestParam Integer mass,
                         @Valid Zoo zoo,
                         BindingResult bindingResult,
                         Model model){

        if (bindingResult.hasErrors()){
            return "zoo/add";
        }

        zooRepository.save(zoo);
        return "redirect:/zoo";
    }

    @GetMapping("/filter")
    public String zooFilter(@RequestParam String searchName,
                            Model model){
        List<Zoo> zoo = zooRepository.findByNameContaining(searchName);
        model.addAttribute("animal", zoo);
        return "zoo/zoo-filter";
    }

    @GetMapping("/detail/{id}")
    public String zooDetail(@PathVariable() Long id, Model model){
        ArrayList<Zoo> res = new ArrayList<>();
        zooRepository.findById(id).ifPresent(res::add);
        model.addAttribute("animal", res);
        return "/zoo/zoo-detail";
    }

    @GetMapping("/del/{id}")
    public String zooDelete(@PathVariable() Long id){
        zooRepository.deleteById(id);
        return "redirect:/zoo";
    }

    @GetMapping("/edit/{id}")
    public String zooEdit(@PathVariable() Long id, Model model){
        Zoo animal = zooRepository.findById(id).orElseThrow();
        model.addAttribute("animal", animal);
        return "/zoo/zoo-edit";
    }

    @PostMapping("/edit/{id}")
    public String zooEditConc(@PathVariable() Long id, @RequestParam String name,
                              @RequestParam String description,
                              @RequestParam Integer age,
                              @RequestParam Integer mass){
        Zoo zoo = new Zoo(name, age, description, mass);
        zoo.setId(id);
        zooRepository.save(zoo);
        return "redirect:/zoo/detail/" + id;
    }
}
