package com.csc3402.dbproject.repository;

import com.csc3402.dbproject.model.Category;
import com.csc3402.dbproject.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "SELECT * FROM orders where order_id = :id", nativeQuery = true)
    Category findOrderById(@Param("id") int id);

    @Query(value = "SELECT order_id FROM orders WHERE customer_id = :customer_id ORDER BY last_updated DESC LIMIT 1", nativeQuery = true)
    Long getCustomerLatestOrderId(@Param("customer_id") int customer_id);
}