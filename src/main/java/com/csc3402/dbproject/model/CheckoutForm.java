package com.csc3402.dbproject.model;

import java.util.List;
import com.csc3402.dbproject.model.OrderProduct;
import com.csc3402.dbproject.model.Order;

// wrapped object to hold checkout data, for handling them in th:object
public class CheckoutForm {
    private List<OrderProduct> itemList;
    private Order order;

    public CheckoutForm() {
    }

    public CheckoutForm(List<OrderProduct> itemList, com.csc3402.dbproject.model.Order order) {
        this.itemList = itemList;
        this.order = order;
    }

    public List<OrderProduct> getItemList() {
        return itemList;
    }

    public void setItemList(List<OrderProduct> itemList) {
        this.itemList = itemList;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String toString() {
        return itemList.toString() + "\n" + order.toString();
    }
}