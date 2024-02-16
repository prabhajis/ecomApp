package com.demo.ecomApp.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class ShelfId implements Serializable {

    private String shopperId;
    private String productId;

    // Override equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShelfId shelfId = (ShelfId) o;
        return Objects.equals(shopperId, shelfId.shopperId) &&
                Objects.equals(productId, shelfId.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shopperId, productId);
    }
}