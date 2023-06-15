package com.csc3402.dbproject.controller;

import com.csc3402.dbproject.repository.OrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {
    private final OrderRepository orderRepository;

    public CheckoutController(OrderRepository orderRepository) {this.orderRepository = orderRepository;}

    @GetMapping("edit")
    public String checkoutOrder(@RequestParam("order_id") long order_id) {
        return "checkout";
    }
}
