package com.csc3402.dbproject.controller;

import com.csc3402.dbproject.model.CheckoutForm;
import com.csc3402.dbproject.repository.OrderProductRepository;
import com.csc3402.dbproject.repository.OrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.csc3402.dbproject.model.Order;
import com.csc3402.dbproject.model.OrderProduct;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/orderlist")
public class OrderListController {

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private Integer customer_id = 2;

    public OrderListController(OrderRepository orderRepository, OrderProductRepository orderProductRepository) {
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
    }

    // view all completed orders
    @GetMapping("all")
    public String allOrders(Model model) {
        try{
            List<CheckoutForm> data = new ArrayList<>();
            List<Order> allOrders = orderRepository.getCustomerAllOrder(customer_id);

            if (allOrders == null || allOrders.size() ==0) {
                model.addAttribute("has_item", "false");
            } else {
                for(Order o : allOrders) {
                    Order currentOrder = o;
                    List<OrderProduct> currentProducts = orderProductRepository.getProductsInCart((int) o.getOrderId());

                    // no product found for this order
                    if (currentProducts == null || currentProducts.size() == 0){
                        continue;
                    }

                    CheckoutForm currentCheckoutForm = new CheckoutForm(currentProducts, currentOrder);
                    data.add(currentCheckoutForm);
                }
                if (data.size() > 0){
                    model.addAttribute("has_item", "true");
                    model.addAttribute("data", data);
                } else{
                    model.addAttribute("has_item", "false");
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
            model.addAttribute("has_item", "false");
        }

        return "all-order-list";
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
