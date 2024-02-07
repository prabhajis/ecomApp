package com.demo.ecomApp.dto;

import lombok.Data;

import java.util.List;

@Data
public class ShelfDto {
        private String shopperId;
        private List<ProductShelfItemDto> shelf;
}
