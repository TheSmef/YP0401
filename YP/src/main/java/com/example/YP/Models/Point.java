package com.example.YP.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Поле должно быть пустым")
    @NotBlank(message = "Должно содержать не только пробелы")
    @Size(min = 1, max = 150, message = "Поле должно быть от 1 до 150 символов")
    private String address;

    public Point() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public Collection<OrderPurchase> getOrderPurchases() {
        return orderPurchases;
    }

    public void setOrderPurchases(Collection<OrderPurchase> orderPurchases) {
        this.orderPurchases = orderPurchases;
    }

    public Point(String address, String schedule, Collection<OrderPurchase> orderPurchases) {
        this.address = address;
        this.schedule = schedule;
        this.orderPurchases = orderPurchases;
    }

    @NotEmpty(message = "Поле должно быть пустым")
    @NotBlank(message = "Должно содержать не только пробелы")
    @Size(min = 1, max = 150, message = "Поле должно быть от 1 до 150 символов")
    private String schedule;
    @OneToMany(mappedBy = "point", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Collection<OrderPurchase> orderPurchases;
}
