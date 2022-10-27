package com.example.YP.Repository;

import com.example.YP.Models.Employee;
import com.example.YP.Models.OrderPurchase;
import com.example.YP.Models.Point;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderPurchaseRepository extends CrudRepository<OrderPurchase, Long> {
    public List<OrderPurchase> findByPoint(Point point);
}
