package com.demo.ecomApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Entity(name = "shelves")
@Table(name = "shelves")
@IdClass(ShelfId.class)
public class ShelfEntity {
    @Id
    @Column(name = "shopper_id")
    private String shopperId;

    @Id
    @Column(name = "product_id")
    private String productId;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "productId", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ProductsEntity products;

    private Double relevancyScore;

    public ShelfEntity(String shopperId, String productId, Double relevancyScore) {
        this.shopperId = shopperId;
        this.productId = productId;
        this.relevancyScore = relevancyScore;
    }

    public ShelfEntity() {
    }
}
