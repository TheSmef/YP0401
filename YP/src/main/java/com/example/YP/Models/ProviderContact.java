package com.example.YP.Models;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Entity
public class ProviderContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Поле должно быть пустым")
    @NotBlank(message = "Должно содержать не только пробелы")
    @Size(min = 1, max = 50, message = "Поле должно быть от 1 до 50 символов")
    private String lastname;
    @NotEmpty(message = "Поле должно быть пустым")
    @NotBlank(message = "Должно содержать не только пробелы")
    @Size(min = 1, max = 50, message = "Поле должно быть от 1 до 50 символов")
    private String firstname;
    @Size(min = 0, max = 50, message = "Поле должно быть от 1 до 50 символов")
    private String otch;
    @NotEmpty(message = "Поле должно быть пустым")
    @NotBlank(message = "Должно содержать не только пробелы")
    @Size(min = 11, max = 11, message = "Поле должно быть 11 символов")
    private String number;
    @OneToOne(optional = true, mappedBy = "providerContact", cascade = CascadeType.ALL)
    private ProviderCompany owner;

    public ProviderContact() {
    }

    public ProviderContact(String lastname, String firstname, String otch, String number, ProviderCompany owner) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.otch = otch;
        this.number = number;
        this.owner = owner;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public ProviderCompany getOwner() {
        return owner;
    }

    public void setOwner(ProviderCompany owner) {
        this.owner = owner;
    }
}
