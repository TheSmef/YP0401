package com.example.YP.Controllers;

import com.example.YP.Models.Employee;
import com.example.YP.Models.Post;
import com.example.YP.Models.Zoo;
import com.example.YP.Repository.EmployeeRepository;
import com.example.YP.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/employee")
    public String main(Model model){
        Iterable<Employee> listEmployee = employeeRepository.findAll();
        model.addAttribute("listEmployee", listEmployee);
        return "employee/Index";
    }

    @GetMapping("employee/add")
    public String AddView(Model model, Employee employee){
        return "employee/add";
    }
    @PostMapping("employee/add")
    public String postAdd( @Valid Employee employee,
            BindingResult bindingResult
            ,Model model){
        if (bindingResult.hasErrors()){
            return "employee/add";
        }
        employeeRepository.save(employee);
        return "redirect:/employee";
    }

    @GetMapping("employee/filter-direct")
    public String Filter(@RequestParam String searchName,
                             Model model){
        List<Employee> employee = employeeRepository.findByLastname(searchName);
        model.addAttribute("listEmployee", employee);
        return "employee/filter";
    }

    @GetMapping("employee/filter-contains")
    public String FilterContains(@RequestParam String searchName,
                                     Model model){
        List<Employee> employee = employeeRepository.findByLastnameContaining(searchName);
        model.addAttribute("listEmployee", employee);
        return "employee/filter";
    }

    @GetMapping("employee/detail/{id}")
    public String Detail(@PathVariable() Long id, Model model){
        ArrayList<Employee> res = new ArrayList<>();
        employeeRepository.findById(id).ifPresent(res::add);
        model.addAttribute("em", res);
        return "employee/detail";
    }

    @GetMapping("employee/del/{id}")
    public String Delete(@PathVariable() Long id){
        employeeRepository.deleteById(id);
        return "redirect:/employee";
    }

    @GetMapping("employee/edit/{id}")
    public String Edit(@PathVariable() Long id, Model model){
        Employee employee = employeeRepository.findById(id).orElseThrow();
        model.addAttribute("employee", employee);
        return "employee/edit";
    }

    @PostMapping("employee/edit/{id}")
    public String EditConc(@PathVariable() Long id, @Valid Employee employee, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("employee", employee);
            return "employee/edit";
        }
        employee.setId(id);
        employeeRepository.save(employee);
        return "redirect:/employee/detail/" + id;
    }
}
