package com.example.YP.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Employee {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
    @NotEmpty(message = "Поле должно быть пустым")
    @NotBlank(message = "Должно содержать не только пробелы")
    @Size(min = 1, max = 150, message = "Поле должно быть от 1 до 150 символов")
        private String lastname;
    @NotEmpty(message = "Поле должно быть пустым")
    @NotBlank(message = "Должно содержать не только пробелы")
    @Size(min = 1, max = 150, message = "Поле должно быть от 1 до 150 символов")
        private String firstname;
        private String otch;
    @Max(value =  100, message = "Работнику может быть не более 100 лет")
    @Min(value = 18, message = "Ты несовершеннолетный, иди от сюда")
    @NotNull(message = "Не может быть пустым")
        private Integer age;
    @Size(min=10, max=10, message = "Номер и серия паспорта - 10 символов")
    @NotNull(message = "Не может быть пустым")
        private String passport;

    public Employee() {
    }

    public Employee(String lastname, String firstname, String otch, Integer age, String passport) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.otch = otch;
        this.age = age;
        this.passport = passport;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getOtch() {
        return otch;
    }

    public void setOtch(String otch) {
        this.otch = otch;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }
}
