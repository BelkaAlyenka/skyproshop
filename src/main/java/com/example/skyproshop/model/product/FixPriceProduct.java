package com.example.skyproshop.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product {

    private static final int FIXED_PRICE = 1500;

    public FixPriceProduct(UUID id, String productName) {
        super(id, productName);
    }

    @Override
    public int getProductPrice() {
        return FIXED_PRICE;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return getProductName() + " c фиксированной ценой:" + getProductPrice();
    }
}
