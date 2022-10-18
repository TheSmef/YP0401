package com.example.YP.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @NotBlank(message = "Должно содержать в себе описание")
    private String description;
    private String work_schedule;

    @DecimalMax(value =  "1")
    @DecimalMin(value = "0.01")
    @NotNull(message = "Не может быть пустым")
    private Double share;

    public Post(String name, Double salary, String description, String work_schedule, double share) {
        this.name = name;
        this.salary = salary;
        this.description = description;
        this.work_schedule = work_schedule;
        this.share = share;
    }

    public Post() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWork_schedule() {
        return work_schedule;
    }

    public void setWork_schedule(String work_schedule) {
        this.work_schedule = work_schedule;
    }

    public Double getShare() {return share;}

    public void setShare(double share) {
        this.share = share;
    }
}
