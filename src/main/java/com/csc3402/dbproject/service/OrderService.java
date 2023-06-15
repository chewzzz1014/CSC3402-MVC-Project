package com.csc3402.dbproject.service;

import com.csc3402.dbproject.repository.OrderProductRepository;
import org.springframework.stereotype.Service;
import com.csc3402.dbproject.model.Order;
import com.csc3402.dbproject.model.Product;

@Service
public class OrderService {
    private final OrderProductRepository orderProductRepository;

    public OrderService(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    public void deleteOrderProduct(Order order, Product product) {
        orderProductRepository.deleteByOrderAndProduct(order, product);
    }
}
