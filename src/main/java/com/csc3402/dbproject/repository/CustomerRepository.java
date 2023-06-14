package com.csc3402.dbproject.repository;

import com.csc3402.dbproject.model.Category;
import com.csc3402.dbproject.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    // Find customers by id
    @Query(value = "SELECT * FROM customer where customer_id = :id", nativeQuery = true)
    Category findByCustomerId(@Param("id") int id);

}