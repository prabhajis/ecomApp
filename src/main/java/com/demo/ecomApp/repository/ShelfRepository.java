package com.demo.ecomApp.repository;

import com.demo.ecomApp.entity.ProductsEntity;
import com.demo.ecomApp.entity.ShelfEntity;
import com.demo.ecomApp.entity.ShelfId;
import com.demo.ecomApp.entity.ShopperEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShelfRepository extends JpaRepository<ShelfEntity, ShelfId> {

    @Query("SELECT s FROM shelves s JOIN products p ON s.productId = :productId")
    List<ShelfEntity> getShelvesByProducts(
            @Param("productId") String productId,
            Pageable pageable
    );
}
