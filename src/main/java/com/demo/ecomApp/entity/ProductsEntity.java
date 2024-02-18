package com.demo.ecomApp.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table(name = "products")
@Entity(name = "products")
public class ProductsEntity implements Serializable {
    private static final long serialVersionUID = -4438114469417994311L;

    @Id
    private String productId;
    private String category;
    private String brand;
}
