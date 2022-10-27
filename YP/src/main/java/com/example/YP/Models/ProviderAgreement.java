package com.example.YP.Models;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class ProviderAgreement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Date date;
    @ManyToOne(optional = true)
    private ProviderCompany company;
    @OneToMany(mappedBy = "providerAgreement", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Collection<Purchase> purchases;

    public ProviderAgreement() {
    }

    public ProviderAgreement(Date date, ProviderCompany company, Collection<Purchase> purchases) {
        this.date = date;
        this.company = company;
        this.purchases = purchases;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ProviderCompany getCompany() {
        return company;
    }

    public void setCompany(ProviderCompany company) {
        this.company = company;
    }

    public Collection<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(Collection<Purchase> purchases) {
        this.purchases = purchases;
    }
}
