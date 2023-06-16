package com.csc3402.dbproject.model;

import java.util.List;
import com.csc3402.dbproject.model.OrderProduct;
import com.csc3402.dbproject.model.Order;

// wrapped object to hold cart data, for handling them in th:object
public class CheckoutForm {
    private List<OrderProduct> cartList;
    private Order order;

    public CheckoutForm(List<OrderProduct> cartList, Order order) {
        this.order = order;
        this.cartList = cartList;
    }

    public List<OrderProduct> getCartList() {
        return cartList;
    }

    public Order getOrder() {return order;}

    public void setCartList(List<OrderProduct> cartList) {
        this.cartList = cartList;
    }

    public void setOrder(Order order) {this.order = order;}
}