package com.example.YP.Models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Поле должно быть пустым")
    @Size(min = 1, max = 30, message = "Поле должно быть от 1 до 30 символов")
    private String name;
    @DecimalMax(value =  "1000000000.99")
    @DecimalMin(value = "0.00")
    @NotNull(message = "Не может быть пустым")
    private Double salary;

    @OneToOne(optional = true, mappedBy = "post")
    private Employee employee;



    public Post() {
    }


    public Post(String name, Double salary) {
        this.name = name;
        this.salary = salary;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }


    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
