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

    public CheckoutForm(List<com.csc3402.dbproject.model.OrderProduct> itemList, com.csc3402.dbproject.model.Order order) {
        this.itemList = itemList;
        this.order = order;
    }

    public List<com.csc3402.dbproject.model.OrderProduct> getItemList() {
        return itemList;
    }

    public void setItemList(List<com.csc3402.dbproject.model.OrderProduct> itemList) {
        this.itemList = itemList;
    }

    public com.csc3402.dbproject.model.Order getOrder() {
        return order;
    }

    public void setOrder(com.csc3402.dbproject.model.Order order) {
        this.order = order;
    }

    public String toString() {
        return itemList.toString() + "\n" + order.toString();
    }
}