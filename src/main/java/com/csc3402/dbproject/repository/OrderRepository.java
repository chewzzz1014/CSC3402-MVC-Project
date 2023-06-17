package com.csc3402.dbproject.repository;

import com.csc3402.dbproject.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.csc3402.dbproject.model.Order;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "SELECT * FROM orders where order_id = :id", nativeQuery = true)
    Category findOrderById(@Param("id") int id);

    @Query(value = "SELECT COALESCE(order_id, -1) FROM orders WHERE customer_id = :customer_id AND has_check_out = 0 LIMIT 1", nativeQuery = true)
    Long getCustomerLatestOrderId(@Param("customer_id") int customer_id);

    @Query(value = "SELECT COALESCE(order_id, -1) FROM orders WHERE customer_id = :customer_id AND has_check_out = 0 LIMIT 1", nativeQuery = true)
    Integer getCustomerLatestOrderId2(@Param("customer_id") int customer_id);

    @Query(value = "SELECT * FROM orders WHERE customer_id = :customer_id AND has_check_out = 1", nativeQuery = true)
    List<Order> getCustomerAllOrder(@Param("customer_id") int customer_id);
}