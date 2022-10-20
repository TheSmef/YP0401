package com.example.YP.Models;

import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Zoo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Поле должно быть пустым")
    @Size(min = 1, max = 150, message = "Поле должно быть от 1 до 150 символов")
    private String name;
    @Max(value =  1000)
    @Min(value = 1)
    @NotNull(message = "Не может быть пустым")
    private Integer age;
    @Null
    @NotBlank
    private String description;
    @Positive(message = "Фантомное животное")
    @NotNull
    private Integer mass;

//    @PositiveOrZero
//    @Negative
//    @NegativeOrZero
//
//
//    @DecimalMax(value = 15)
//    @DecimalMin(value = 2)
//
//    @Future
//    @FutureOrPresent
//    @Past
//    @PastOrPresent
//
//    @Email
//
//    @AssertTrue
//    @AssertFalse

    public Zoo(String name, Integer age,
               String description, Integer mass) {
        this.name = name;
        this.age = age;
        this.description = description;
        this.mass = mass;
    }

    public Zoo() {
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMass() {
        return mass;
    }

    public void setMass(Integer mass) {
        this.mass = mass;
    }
}
