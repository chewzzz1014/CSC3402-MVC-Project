package com.csc3402.dbproject.model;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "total_price")
    private String totalprice;

    @Column(name = "date_of_order")
    private String date;

    @Column(name = "payment_method")
    private Integer paymentmethod;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(name = "orders_products",
            joinColumns = {
                    @JoinColumn(name = "order_id", referencedColumnName = "order_id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "product_id", referencedColumnName = "product_id",
                            nullable = false, updatable = false)})

    private Set<Product> products = new HashSet<>();

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getPaymentmethod() {
        return paymentmethod;
    }

    public void setPaymentmethod(Integer paymentmethod) {
        this.paymentmethod = paymentmethod;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Order() {
    }

    public Order(String totalprice, String date, Integer paymentmethod, User user) {
        this.totalprice = totalprice;
        this.date = date;
        this.paymentmethod = paymentmethod;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", totalprice='" + totalprice + '\'' +
                ", date='" + date + '\'' +
                ", paymentmethod=" + paymentmethod +
                '}';
    }
}