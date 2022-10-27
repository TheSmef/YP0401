package com.example.YP.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

@Entity
public class OrderPurchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public OrderPurchase() {
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Collection<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(Collection<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public OrderPurchase(Date date, Employee employee, Collection<OrderProduct> orderProducts, Point point) {
        this.date = date;
        this.employee = employee;
        this.orderProducts = orderProducts;
        this.point = point;
    }

    @NotNull
    private Date date;
    @ManyToOne(optional = false)
    private Employee employee;
    @OneToMany(mappedBy = "orderPurchase", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Collection<OrderProduct> orderProducts;
    @ManyToOne(optional = true, cascade = CascadeType.REMOVE)
    private Point point;

}
