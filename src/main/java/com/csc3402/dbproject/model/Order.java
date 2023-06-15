package com.csc3402.dbproject.model;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "total_price", columnDefinition = "integer default 0")
    private Integer totalprice;

    @Column(name = "last_updated", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date last_updated;

    @Column(name = "payment_method")
    private String paymentmethod;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_num")
    private String phonenum;

    @Column(name = "has_check_out", columnDefinition = "integer default 0")
    private Integer hascheckout;

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

    public Date getDate() {
        return last_updated;
    }

    public void setDate(Date date) {
        this.last_updated = date;
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

    public String getPaymentmethod() {
        return paymentmethod;
    }

    public void setPaymentmethod(String paymentmethod) {
        this.paymentmethod = paymentmethod;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getHascheckout() {
        return hascheckout;
    }

    public void setHascheckout(Integer hascheckout) {
        this.hascheckout = hascheckout;
    }

    public Order() {
        super();
    }

    public Order(Integer totalprice, Date date, String paymentmethod, String address, String phonenum, Customer customer, Integer hascheckout) {
        super();
        this.totalprice = totalprice;
        this.last_updated = date;
        this.paymentmethod = paymentmethod;
        this.address = address;
        this.phonenum = phonenum;
        this.customer = customer;
        this.hascheckout = hascheckout;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", totalprice='" + totalprice + '\'' +
                ", date='" + last_updated + '\'' +
                ", paymentmethod=" + paymentmethod +
                ", address='" + address + '\'' +
                ", phonenum='" + phonenum + '\'' +
                ", customer=" + customer +
                ", hascheckout=" + hascheckout +
                '}';
    }
}
