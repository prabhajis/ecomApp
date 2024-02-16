package com.demo.ecomApp.repository;

import com.demo.ecomApp.entity.ProductsEntity;
import com.demo.ecomApp.entity.ShelfEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductsEntity, String> {
    @Query("SELECT p FROM products p " +
            "JOIN shelves s ON p.productId = s.productId " +
            "WHERE s.shopperId = :shopperId " +
            "AND (:category IS NULL OR p.category = :category) " +
            "AND (:brand IS NULL OR p.brand = :brand) " +
            "ORDER BY s.relevancyScore DESC")
    List<ProductsEntity> getProductsByShopper(
            @Param("shopperId") String shopperId,
            @Param("category") String category,
            @Param("brand") String brand,
            Pageable pageable
    );

    Optional<ProductsEntity> findByProductId(
            @Param("productId") String productId
    );

    @Query("SELECT DISTINCT p.productId FROM products p")
    List<String> getProductIds();
}
