package com.example.YP.Models;

import javax.persistence.*;
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
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Post post;



    public Employee() {

    }

    public Employee(String lastname, String firstname) {
        this.lastname = lastname;
        this.firstname = firstname;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
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

}
