package com.csc3402.dbproject.controller;

import com.csc3402.dbproject.model.Product;
import com.csc3402.dbproject.repository.OrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final OrderRepository orderRepository;

    CartController(OrderRepository orderRepository) {this.orderRepository = orderRepository;}

    @GetMapping("display/{customer_id}")
    public String displayCart(@PathVariable("customer_id") long id, Model model){
        // get the customer's order id
        // then get all products in the cart using the order id obtained
//        List<Product> orderProducts = (List<Product>) orderRepository.findStaffProjectByStaffId((int) id);

        return "cart";
    }

    // add to cart

}
