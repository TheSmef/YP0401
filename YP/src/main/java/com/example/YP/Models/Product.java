package com.example.YP.Models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Поле должно быть пустым")
    @NotBlank(message = "Должно содержать не только пробелы")
    @Size(min = 1, max = 150, message = "Поле должно быть от 1 до 150 символов")
    private String name;
    @DecimalMax(value =  "1000000000.99")
    @DecimalMin(value = "0.00")
    @NotNull(message = "Не может быть пустым")
    private Double cost;

    public Product(String name, Double cost, int expire, Category category) {
        this.name = name;
        this.cost = cost;
        this.expire = expire;
        this.category = category;
    }

    public Product() {
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

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Min(0)
    private int expire;
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Category category;
}
