package com.example.YP.Repository;

import com.example.YP.Models.Category;
import com.example.YP.Models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
