package com.example.YP.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class ProviderCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Поле должно быть пустым")
    @NotBlank(message = "Должно содержать не только пробелы")
    @Size(min = 1, max = 150, message = "Поле должно быть от 1 до 150 символов")
    private String name;
    @NotEmpty(message = "Поле должно быть пустым")
    @NotBlank(message = "Должно содержать не только пробелы")
    @Size(min = 1, max = 150, message = "Поле должно быть от 1 до 150 символов")
    private String place;
    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name="providercontact_id")
    private  ProviderContact providerContact;
    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Collection<ProviderAgreement> agreements;

    public ProviderCompany(String name, String place, ProviderContact providerContact, Collection<ProviderAgreement> agreements) {
        this.name = name;
        this.place = place;
        this.providerContact = providerContact;
        this.agreements = agreements;
    }

    public ProviderCompany() {
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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public ProviderContact getProviderContact() {
        return providerContact;
    }

    public void setProviderContact(ProviderContact providerContact) {
        this.providerContact = providerContact;
    }

    public Collection<ProviderAgreement> getAgreements() {
        return agreements;
    }

    public void setAgreements(Collection<ProviderAgreement> agreements) {
        this.agreements = agreements;
    }
}
