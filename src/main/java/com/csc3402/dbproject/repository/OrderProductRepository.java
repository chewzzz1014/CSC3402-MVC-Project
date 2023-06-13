package com.csc3402.dbproject.repository;

import com.csc3402.dbproject.model.OrderProduct;
import com.csc3402.dbproject.model.OrderProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductId> {

    @Query(value = "SELECT * FROM order_product where order_id = :id", nativeQuery = true)
    List<OrderProduct> findOrderProductByOrderId(@Param("id")int id);
}
