package com.demo.ecomApp.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ShelfId implements Serializable {
    private String shopperId;
    private String productId;
}