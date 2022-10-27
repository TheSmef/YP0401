package com.example.YP.Controllers;

import com.example.YP.Models.Employee;
import com.example.YP.Models.Post;
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
@PreAuthorize("hasAnyAuthority('ADMIN', 'CHAR')")
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
        return "employee/Index";
    }

    @GetMapping("employee/filter-contains")
    public String FilterContains(@RequestParam String searchName,
                                     Model model){
        List<Employee> employee = employeeRepository.findByLastnameContaining(searchName);
        model.addAttribute("listEmployee", employee);
        return "employee/Index";
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

    @GetMapping("employee/deletepost/{id}/{idp}")
    public String deletePost(@PathVariable Long id, @PathVariable Long idp){
        Employee employeeTarget = employeeRepository.findById(id).orElseThrow();
        employeeTarget.getPosts().remove(postRepository.findById(idp).orElseThrow());
        employeeRepository.save(employeeTarget);
        return "redirect:/employee/detail/" + id;
    }

    @GetMapping("employee/add")
    public String AddView(Model model, Employee employee){
        return "employee/add";
    }
    @PostMapping("employee/add")
    public String Add(@Valid Employee employee,
                          BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("employee", employee);
            return "employee/add";
        }
        employeeRepository.save(employee);
        return "redirect:/employee";
    }

    @GetMapping("employee/edit/{id}")
    public String Edit(@PathVariable() Long id, Model model){
        Employee employee = employeeRepository.findById(id).orElseThrow();
        model.addAttribute("employee", employee);
        return "employee/edit";
    }

    @PostMapping("employee/edit/{id}")
    public String EditConc(@Valid Employee employee, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("employee", employee);
            return "employee/edit";
        }
        employee.setOwner(employeeRepository.findById(employee.getId()).orElseThrow().getOwner());
        employee.setPosts(employeeRepository.findById(employee.getId()).orElseThrow().getPosts());
        employeeRepository.save(employee);
        return "redirect:/employee/detail/" + employee.getId();
    }
}
