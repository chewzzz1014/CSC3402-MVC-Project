package com.csc3402.dbproject.model;

import java.util.List;
import com.csc3402.dbproject.model.OrderProduct;

// wrapped object to hold cart data, for handling them in th:object
public class CartForm {
    private List<OrderProduct> cartList;

    public CartForm(List<OrderProduct> cartList) {
        this.cartList = cartList;
    }
    
    public List<OrderProduct> getCartList() {
        return cartList;
    }
    
    public void setCartList(List<OrderProduct> cartList) {
        this.cartList = cartList;
    }
}
