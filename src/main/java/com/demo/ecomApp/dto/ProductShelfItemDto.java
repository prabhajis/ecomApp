package com.demo.ecomApp.dto;

import lombok.Data;

@Data
public class ProductShelfItemDto {
    private String productId;
    private double relevancyScore;
}
