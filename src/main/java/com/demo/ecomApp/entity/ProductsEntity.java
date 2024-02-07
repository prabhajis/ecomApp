package com.demo.ecomApp.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "products")
@Entity(name = "products")
public class ProductsEntity {
    @Id
    private String productId;
    private String category;
    private String brand;
}
