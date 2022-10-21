package com.example.YP.Controllers;

import com.example.YP.Models.Employee;
import com.example.YP.Models.Post;
import com.example.YP.Models.Zoo;
import com.example.YP.Repository.EmployeeRepository;
import com.example.YP.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@PreAuthorize("hasAnyAuthority('CHAR')")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PostRepository postRepository;

    @GetMapping("/employee")
    public String main(Model model){
        Iterable<Employee> listEmployee = employeeRepository.findAll();
        model.addAttribute("listEmployee", listEmployee);
        return "employee/Index";
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

    @GetMapping("employee/addpost")
    public String addPostView(Model model){
        Iterable<Post> post = postRepository.findAll();
        List<Post> postArrayList = new ArrayList<>();
        for (Post pass:
                post) {
            postArrayList.add(pass);
        }
        Iterable<Employee> employees = employeeRepository.findAll();
        List<Employee> employeeArrayList = new ArrayList<>();
        for (Employee em:
                employees) {
            employeeArrayList.add(em);
        }
        model.addAttribute("employee", employeeArrayList);
        model.addAttribute("posts", postArrayList);
        return "employee/addpost";
    }
    @PostMapping("employee/addpost")
    public String addPost(@RequestParam Long post, @RequestParam Long employee){
        Employee employeeTarget = employeeRepository.findById(employee).orElseThrow();
        employeeTarget.getPosts().add(postRepository.findById(post).orElseThrow());
        employeeRepository.save(employeeTarget);
        return "redirect:/employee";
    }
}
