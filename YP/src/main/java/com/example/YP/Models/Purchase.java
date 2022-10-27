package com.example.YP.Models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Min(0)
    private int amount;
    @DecimalMax(value =  "1000000000.99")
    @DecimalMin(value = "0.00")
    @NotNull(message = "Не может быть пустым")
    private Double cost;
    @ManyToOne(optional = true)
    private ProviderAgreement providerAgreement;
    @ManyToOne(optional = true)
    private Product product;

    public Purchase(int amount, Double cost, ProviderAgreement providerAgreement, Product product) {
        this.amount = amount;
        this.cost = cost;
        this.providerAgreement = providerAgreement;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public ProviderAgreement getProviderAgreement() {
        return providerAgreement;
    }

    public void setProviderAgreement(ProviderAgreement providerAgreement) {
        this.providerAgreement = providerAgreement;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Purchase() {
    }
}
