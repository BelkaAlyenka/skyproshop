package com.example.skyproshop.model.basket;

import java.util.List;

public final class UserBasket {
    private final List<BasketItem> items;
    private final int total;

    public UserBasket(List<BasketItem> items) {
        this.items = items;
        this.total = calculateSum(items);
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public int getTotal() {
        return total;
    }

    private int calculateSum(List<BasketItem> basketItems) {
        return basketItems.stream()
                .mapToInt(item -> item.getProduct().getProductPrice() * item.getQuantity())
                .sum();
    }
}