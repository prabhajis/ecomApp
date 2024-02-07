package com.demo.ecomApp.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "shelves")
@Table(name = "shelves")
@IdClass(ShelfId.class)
public class ShelfEntity {
    @Id
    private String shopperId;
    @Id
    private String productId;
    private Double relevancyScore;

}
