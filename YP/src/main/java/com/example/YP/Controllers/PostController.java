package com.example.YP.Controllers;

import com.example.YP.Models.Post;
import com.example.YP.Models.Zoo;
import com.example.YP.Repository.PostRepository;
import com.example.YP.Repository.ZooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
