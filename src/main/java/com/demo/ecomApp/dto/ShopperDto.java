package com.demo.ecomApp.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ShopperDto implements Serializable {
    private static final long serialVersionUID = -4439114469417994311L;
    private String shopperId;
    private double relevancyScore;

}
