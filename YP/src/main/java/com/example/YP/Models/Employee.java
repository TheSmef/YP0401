package com.example.YP.Models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.List;

@Entity
public class Employee {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
    @NotEmpty(message = "Поле не должно быть пустым")
    @NotBlank(message = "Должно содержать не только пробелы")
    @Size(min = 1, max = 50, message = "Поле должно быть от 1 до 50 символов")
        private String lastname;
    @NotEmpty(message = "Поле не должно быть пустым")
    @NotBlank(message = "Должно содержать не только пробелы")
    @Size(min = 1, max = 50, message = "Поле должно быть от 1 до 50 символов")
        private String firstname;
    @Size(min = 0, max = 50, message = "Поле должно быть от 1 до 50 символов")
        private String otch;
    @NotEmpty(message = "Поле не должно быть пустым")
    @NotBlank(message = "Должно содержать не только пробелы")
    @Size(min = 10, max = 10, message = "Поле должно быть 10 символов")
        private String passport;

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Collection<OrderPurchase> orderPurchases;

    @OneToOne(optional = true, mappedBy = "employee", cascade = CascadeType.ALL)
        private User owner;

    public String getOtch() {
        return otch;
    }

    public void setOtch(String otch) {
        this.otch = otch;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passpost) {
        this.passport = passpost;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @ManyToMany
    @JoinTable (name="employee_post",
            joinColumns=@JoinColumn (name="employee_id"),
            inverseJoinColumns=@JoinColumn(name="post_id"))
    private List<Post> posts;


    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Employee() {

    }

    public Employee(String lastname, String firstname) {
        this.lastname = lastname;
        this.firstname = firstname;
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
