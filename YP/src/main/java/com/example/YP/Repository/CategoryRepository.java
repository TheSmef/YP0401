package com.example.YP.Repository;

import com.example.YP.Models.Category;
import com.example.YP.Models.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository
        extends CrudRepository<Category, Long> {
    public List<Category> findByName(String name);
    public List<Category> findByNameContaining(String name);
}
