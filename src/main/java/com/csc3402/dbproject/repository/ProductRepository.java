package com.csc3402.dbproject.repository;

import com.csc3402.dbproject.model.Category;
import com.csc3402.dbproject.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT * FROM products where product_id = :id", nativeQuery = true)
    List<Product> findProductById(@Param("id") int id);

    @Query(value = "SELECT * FROM products where category_id = :id", nativeQuery = true)
    List<Product> filterProductByCategory(@Param("id") int id);
}