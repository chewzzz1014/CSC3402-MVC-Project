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

    @Column(name = "quantity")
    private String quantity;

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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public OrderProduct() {
    }

    public OrderProduct(Order order, Product product, String quantity){
        this.id = new OrderProductId(order.getOrderId(), product.getProductId());
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }


}