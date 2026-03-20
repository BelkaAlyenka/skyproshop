package com.example.skyproshop.model.basket;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@SessionScope
public class ProductBasket {
    private final Map<UUID, Integer> mapProducts = new HashMap<>();

    public void addProduct(UUID id) {
        if (id == null) {
            return;
        }

        mapProducts.computeIfAbsent(id, k -> 0);
        mapProducts.put(id, mapProducts.get(id) + 1);
    }

    public Map<UUID, Integer> getAllProducts() {
        return Collections.unmodifiableMap(new HashMap<>(mapProducts));
    }
}
