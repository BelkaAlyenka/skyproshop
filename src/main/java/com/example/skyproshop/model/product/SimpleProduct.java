package com.example.skyproshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product {

    private final int productPrice;

    public SimpleProduct(UUID id, String productName, int productPrice) {
        super(id, productName);
        if (productPrice <= 0) {
            throw new IllegalArgumentException("Цена должна быть строго больше 0");
        }
        this.productPrice = productPrice;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public int getProductPrice() {
        return productPrice;
    }

    @Override
    public String toString() {
        return getProductName() + ":" + getProductPrice();
    }
}
