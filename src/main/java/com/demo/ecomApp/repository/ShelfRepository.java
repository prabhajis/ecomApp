package com.demo.ecomApp.repository;

import com.demo.ecomApp.entity.ShelfEntity;
import com.demo.ecomApp.entity.ShelfId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShelfRepository extends JpaRepository<ShelfEntity, ShelfId> {

    @Query("SELECT s FROM shelves s JOIN products p ON s.productId = :productId")
    List<ShelfEntity> getShelvesByProducts(
            @Param("productId") String productId,
            Pageable pageable
    );

    Optional<ShelfEntity> findByShopperIdAndProductId(
            @Param("shopperId") String shopperId,
            @Param("productId") String productId
    );


    @Modifying
    @Transactional
    @Query(value = "delete from shelves b where b.shopperId=:shopperId and b.productId=:productId")
    void deleteShelvesByShopperIdAndProductId(@Param("shopperId") String shopperId, @Param("productId") String productId);
}
