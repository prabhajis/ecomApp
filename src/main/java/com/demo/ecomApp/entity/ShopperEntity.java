package com.demo.ecomApp.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class ShopperEntity {
    @Id
    private String shopperId;

}
