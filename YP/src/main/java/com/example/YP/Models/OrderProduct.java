package com.example.YP.Models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Min(1)
    private int amount;
    @ManyToOne(optional = true)
    private Product product;
    @ManyToOne(optional = true)
    private OrderPurchase orderPurchase;

    public OrderProduct() {
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public OrderPurchase getOrderPurchase() {
        return orderPurchase;
    }

    public void setOrderPurchase(OrderPurchase orderPurchase) {
        this.orderPurchase = orderPurchase;
    }

    public OrderProduct(int amount, Product product, OrderPurchase orderPurchase) {
        this.amount = amount;
        this.product = product;
        this.orderPurchase = orderPurchase;
    }
}
