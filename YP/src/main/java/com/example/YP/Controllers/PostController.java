package com.example.YP.Controllers;

import com.example.YP.Models.Employee;
import com.example.YP.Models.Post;
import com.example.YP.Models.Zoo;
import com.example.YP.Repository.PostRepository;
import com.example.YP.Repository.ZooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    @Autowired
    PostRepository postRepository;

    @GetMapping("/post")
    public String main(Model model){
        Iterable<Post> listPost = postRepository.findAll();
        model.addAttribute("listPost", listPost);
        return "post/Index";
    }

    @GetMapping("post/add")
    public String postAddView(Model model){
        return "post/add";
    }
    @PostMapping("post/add")
    public String postAdd(@RequestParam String name,
                         @RequestParam String description,
                         @RequestParam Double share,
                         @RequestParam String workschedule,
                         @RequestParam Double salary,Model model){
        Post post = new Post(name, salary,description, workschedule, share);
        postRepository.save(post);
        return "redirect:/post";
    }

    @GetMapping("post/filter-direct")
    public String postFilter(@RequestParam String searchName,
                            Model model){
        List<Post> post = postRepository.findByName(searchName);
        model.addAttribute("listPost", post);
        return "post/filter";
    }

    @GetMapping("post/filter-contains")
    public String postFilterContains(@RequestParam String searchName,
                            Model model){
        List<Post> post = postRepository.findByNameContaining(searchName);
        model.addAttribute("listPost", post);
        return "post/filter";
    }

    @GetMapping("post/detail/{id}")
    public String Detail(@PathVariable() Long id, Model model){
        ArrayList<Post> res = new ArrayList<>();
        postRepository.findById(id).ifPresent(res::add);
        model.addAttribute("post", res);
        return "post/detail";
    }

    @GetMapping("post/del/{id}")
    public String Delete(@PathVariable() Long id){
        postRepository.deleteById(id);
        return "redirect:/post";
    }

    @GetMapping("post/edit/{id}")
    public String Edit(@PathVariable() Long id, Model model){
        Post post = postRepository.findById(id).orElseThrow();
        model.addAttribute("post", post);
        return "post/edit";
    }

    @PostMapping("post/edit/{id}")
    public String EditConc(@PathVariable() Long id, @RequestParam String name,
                           @RequestParam String description,
                           @RequestParam Double share,
                           @RequestParam String workschedule,
                           @RequestParam Double salary){
        Post post = new Post(name, salary,description, workschedule, share);
        post.setId(id);
        postRepository.save(post);
        return "redirect:/post/detail/" + id;
    }
}
