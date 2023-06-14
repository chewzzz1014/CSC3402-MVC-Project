package com.csc3402.dbproject.model;
import jakarta.persistence.*;

import java.io.Serializable;


@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "total_price", columnDefinition = "integer default 0")
    private Integer totalprice;

    @Column(name = "date_of_order")
    private String date;

    @Column(name = "payment_method", columnDefinition = "varchar(250) default ''")
    private Integer paymentmethod;

    @Column(name = "address", columnDefinition = "varchar(250) default ''")
    private String address;

    @Column(name = "phone_num", columnDefinition = "varchar(250) default ''")
    private String phonenum;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getTotalprice() { return totalprice; }

    public void setTotalprice(Integer totalprice) {
        this.totalprice = totalprice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public Integer getPaymentmethod() {
        return paymentmethod;
    }

    public void setPaymentmethod(Integer paymentmethod) {
        this.paymentmethod = paymentmethod;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Order() {
        super();
    }

    public Order(Integer totalprice, String date, Integer paymentmethod, String address, String phonenum, Customer customer) {
        super();
        this.totalprice = totalprice;
        this.date = date;
        this.paymentmethod = paymentmethod;
        this.address = address;
        this.phonenum = phonenum;
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", totalprice='" + totalprice + '\'' +
                ", date='" + date + '\'' +
                ", paymentmethod=" + paymentmethod +
                ", address='" + address + '\'' +
                ", phonenum='" + phonenum + '\'' +
                ", customer=" + customer +
                '}';
    }
}
