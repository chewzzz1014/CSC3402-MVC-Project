package com.csc3402.dbproject.controller;

import com.csc3402.dbproject.repository.OrderProductRepository;
import com.csc3402.dbproject.repository.OrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.csc3402.dbproject.model.Order;
import com.csc3402.dbproject.model.OrderProduct;

import java.util.List;

@Controller
@RequestMapping("/orderlist")
public class OrderListController {

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;

    public OrderListController(OrderRepository orderRepository, OrderProductRepository orderProductRepository) {
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
    }

    // view the transaction successful order
    @GetMapping("view")
    public String checkoutOrder(@RequestParam("order_id") long order_id, Model model) {
        Order order = orderRepository.findById((int) order_id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + order_id));

        List<OrderProduct> products_in_cart = orderProductRepository.getProductsInCart((int) order_id);

        model.addAttribute("products", products_in_cart);
        model.addAttribute("order", order);

        return "order-list";
    }
}
