package com.csc3402.dbproject.controller;

import com.csc3402.dbproject.model.OrderProduct;
import com.csc3402.dbproject.model.OrderProductId;
import com.csc3402.dbproject.model.Product;
import com.csc3402.dbproject.repository.CustomerRepository;
import com.csc3402.dbproject.repository.OrderProductRepository;
import com.csc3402.dbproject.model.Order;
import java.util.Optional;
import com.csc3402.dbproject.model.Customer;
import com.csc3402.dbproject.repository.OrderRepository;
import com.csc3402.dbproject.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/orderproduct")
public class OrderProductController {
    private final OrderProductRepository orderProductRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private Integer customer_id = 1;

    public OrderProductController(OrderProductRepository orderProductRepository, OrderRepository orderRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.orderProductRepository = orderProductRepository;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/add")
    public RedirectView addProductToCart(@RequestParam("product_id") long product_id, RedirectAttributes attributes) {
        Customer customer = customerRepository.findById(customer_id)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid customer Id:" + customer_id));
        Product product = productRepository.findById((int) product_id)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + product_id));

        long foundOrderId = orderRepository.getCustomerLatestOrderId(customer_id);

        Optional<Order> order = orderRepository.findById((int)foundOrderId);
        Order foundOrder = order
                            .orElseGet(() -> {
                                Order newOrder = new Order();
                                newOrder.setCustomer(customer);
                                orderRepository.save(newOrder);
                                return newOrder;
                            });


        System.out.println(customer);
        System.out.println(product);
        System.out.println(foundOrder);
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setId(new OrderProductId(foundOrder.getOrderId(), (int)product_id));
        orderProduct.setOrder(foundOrder);
        orderProduct.setProduct(product);
        orderProduct.setQuantity(1);
        System.out.println(orderProduct);
        orderProductRepository.save(orderProduct);

        attributes.addAttribute("order_id", foundOrder.getOrderId());
        return new RedirectView("/cart/edit") ;
    }
}
