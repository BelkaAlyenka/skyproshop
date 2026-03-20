package com.example.skyproshop.model.basket;

import com.example.skyproshop.model.product.Product;

public final class BasketItem {
    private final Product product;
    private final int quantity;

    public BasketItem(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Продукт не может иметь значение null");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Количество не может быть отрицательным");
        }

        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}