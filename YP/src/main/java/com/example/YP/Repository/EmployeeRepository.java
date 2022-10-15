package com.example.YP.Repository;

import com.example.YP.Models.Employee;
import com.example.YP.Models.Zoo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository
        extends CrudRepository<Employee, Long> {
    public List<Employee> findByLastname(String name);

    public List<Employee> findByLastnameContaining(String name);
}
