package com.example.YP.Repository;

import com.example.YP.Models.OrderProduct;
import com.example.YP.Models.Purchase;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
}
