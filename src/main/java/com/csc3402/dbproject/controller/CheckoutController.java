package com.csc3402.dbproject.controller;

import com.csc3402.dbproject.model.CartForm;
import com.csc3402.dbproject.model.Order;
import com.csc3402.dbproject.model.OrderProduct;
import com.csc3402.dbproject.repository.OrderProductRepository;
import com.csc3402.dbproject.repository.OrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;

    public CheckoutController(OrderRepository orderRepository, OrderProductRepository orderProductReposiotry) {
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductReposiotry;
    }

    @GetMapping("edit")
    public String checkoutOrder(@RequestParam("order_id") long order_id, Model model) {
        List<OrderProduct> products_in_cart = orderProductRepository.getProductsInCart((int) order_id);
        CartForm cartForm = new CartForm(products_in_cart);

        float total_price = 0;
        for(int i = 0; i<products_in_cart.size(); i++) {
            total_price += products_in_cart.get(i).getProduct().getPrice() * products_in_cart.get(i).getQuantity();
        }

        model.addAttribute("totalPrice", total_price);
        model.addAttribute("order_id", order_id);
        model.addAttribute("cartForm", cartForm);

        return "checkout";
    }
}
