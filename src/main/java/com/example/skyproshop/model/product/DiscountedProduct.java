package com.example.skyproshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {
    private final int basePrice;
    private final int discountPercentages;

    public DiscountedProduct(UUID id, String productName, int basePrice, int discountPercentages) {
        super(id, productName);
        if (basePrice <= 0) {
            throw new IllegalArgumentException("Базовая цена должна быть строго больше 0");
        }
        if (discountPercentages < 0 || discountPercentages > 100) {
            throw new IllegalArgumentException("Процент должен быть числом в диапазоне от 0 до 100 включительно");
        }
        this.basePrice = basePrice;
        this.discountPercentages = discountPercentages;
    }

    @Override
    public int getProductPrice() {
        return (int) (basePrice * (100 - discountPercentages) / 100.0);
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return getProductName() + " со скидкой:" + getProductPrice();
    }
}
