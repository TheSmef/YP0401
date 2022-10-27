package com.example.YP.Repository;

import com.example.YP.Models.Category;
import com.example.YP.Models.OrderProduct;
import org.springframework.data.repository.CrudRepository;

public interface OrderProductRepository extends CrudRepository<OrderProduct, Long> {
}
