package com.csc3402.dbproject.repository;

import com.csc3402.dbproject.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value = "SELECT * FROM category where category_id = :id", nativeQuery = true)
    Category findByCategoryId(@Param("id") int id);
}