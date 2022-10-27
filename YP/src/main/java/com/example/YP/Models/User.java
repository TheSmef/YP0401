package com.example.YP.Models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name="employee_id")
    private Employee employee;
    @NotEmpty(message = "Поле не должно быть пустым")
    @NotBlank(message = "Должно содержать не только пробелы")
    @Size(min = 3, max = 50, message = "Поле должно быть от 1 до 150 символов")
    private String username;
    @NotEmpty(message = "Поле не должно быть пустым")
    @NotBlank(message = "Должно содержать не только пробелы")
    @Size(min = 5, max = 254, message = "Поле должно быть от 1 до 150 символов")
    @Email
    private String email;
    @NotEmpty(message = "Поле не должно быть пустым")
    @NotBlank(message = "Должно содержать не только пробелы")
    @Size(min = 10, max = 256, message = "Поле должно быть от 1 до 256 символов")
    private String password;
    @NotNull
    private Boolean active = true;
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    private Set<Role> roles;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull
    private Date dateCreation = new Date();

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public User(String username, String password, Boolean active, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.roles = roles;
    }

    public User() {
    }
}
