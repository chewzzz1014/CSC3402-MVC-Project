package com.csc3402.dbproject.model;

import jakarta.persistence.*;

@Entity
public class OrderProduct {

    @EmbeddedId
    private OrderProductId id;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_Id")
    private Order order;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_Id")
    private Product product;

    @Column(name = "quantity", columnDefinition = "integer default 0")
    private Integer quantity;

    @Column(name = "has_check_out", columnDefinition = "integer default 0")
    private Integer hascheckout;

    public OrderProductId getId() {
        return id;
    }

    public void setId(OrderProductId id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getHascheckout() {
        return hascheckout;
    }

    public void setHascheckout(Integer hascheckout) {
        this.hascheckout = hascheckout;
    }

    public OrderProduct() {
        this.quantity = 0;
        this.hascheckout = 0;
    }

    public OrderProduct(Integer quantity) {
        this.quantity = quantity;
        this.hascheckout = 0;
    }

    public OrderProduct(Order order, Product product, Integer quantity, Integer hascheckout){
        this.id = new OrderProductId(order.getOrderId(), product.getProductId());
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.hascheckout = hascheckout;
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "id=" + id +
                ", order=" + order +
                ", product=" + product +
                ", quantity='" + quantity + '\'' +
                ", hascheckout=" + hascheckout +
                '}';
    }
}