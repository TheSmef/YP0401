package com.example.YP.Controllers;

import com.example.YP.Models.Category;
import com.example.YP.Models.Employee;
import com.example.YP.Models.Post;
import com.example.YP.Models.Product;
import com.example.YP.Repository.CategoryRepository;
import com.example.YP.Repository.PostRepository;
import com.example.YP.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@PreAuthorize("hasAnyAuthority('ADMIN', 'PURCHASER', 'SELLER')")
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/category")
    public String main(Model model){
        Iterable<Category> categories = categoryRepository.findAll();
        model.addAttribute("category", categories);
        return "category/Index";
    }

    @GetMapping("category/add")
    public String AddView(Model model, Category category){
        return "category/add";
    }
    @PostMapping("category/add")
    public String Add(@Valid Category category,
                          BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("category", category);
            return "category/add";
        }
        categoryRepository.save(category);
        return "redirect:/category";
    }

    @GetMapping("category/filter-direct")
    public String Filter(@RequestParam String searchName,
                             Model model){
        List<Category> categories = categoryRepository.findByName(searchName);
        model.addAttribute("category", categories);
        return "category/Index";
    }

    @GetMapping("category/filter-contains")
    public String FilterContains(@RequestParam String searchName,
                                     Model model){
        List<Category> categories = categoryRepository.findByNameContaining(searchName);
        model.addAttribute("category", categories);
        return "category/Index";
    }

    @GetMapping("category/detail/{id}")
    public String Detail(@PathVariable() Long id, Model model){
        ArrayList<Category> res = new ArrayList<>();
        categoryRepository.findById(id).ifPresent(res::add);
        model.addAttribute("category", res);
        return "category/detail";
    }

    @GetMapping("category/del/{id}")
    public String Delete(@PathVariable() Long id){
        categoryRepository.deleteById(id);
        return "redirect:/category";
    }

    @GetMapping("category/edit/{id}")
    public String Edit(@PathVariable() Long id, Model model){
        Category category = categoryRepository.findById(id).orElseThrow();
        model.addAttribute("category", category);
        return "category/edit";
    }

    @PostMapping("category/edit/{id}")
    public String EditConc(@Valid Category category, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("category", category);
            return "category/edit";
        }
        categoryRepository.save(category);
        return "redirect:/category/detail/" + category.getId();
    }

    @GetMapping("category/add-product/{id}")
    public String productAddView(Model model, Product product){
        return "category/add-product";
    }
    @PostMapping("category/add-product/{id}")
    public String productAdd(@PathVariable() Long id, @Valid Product product,
                      BindingResult bindingResult, Model model){
        product.setId(null);
        product.setCategory(categoryRepository.findById(id).orElseThrow());
        if (bindingResult.hasErrors()){
            model.addAttribute("product", product);
            return "category/add-product";
        }
        productRepository.save(product);
        return "redirect:/category/detail/" + id;
    }

    @GetMapping("category/del-product/{id}")
    public String productDelete(@PathVariable() Long id){
        productRepository.delete(productRepository.findById(id).orElseThrow());
        return "redirect:/category";
    }
}