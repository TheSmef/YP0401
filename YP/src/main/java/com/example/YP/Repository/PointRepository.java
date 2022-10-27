package com.example.YP.Repository;

import com.example.YP.Models.Employee;
import com.example.YP.Models.Point;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PointRepository extends CrudRepository<Point, Long> {
    public List<Point> findByAddress(String address);
    public List<Point> findByAddressContaining(String address);

    public Optional<Point> findById(Long id);

}
