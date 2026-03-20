package com.example.skyproshop.model.product;

import com.example.skyproshop.model.search.Searchable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.UUID;

public abstract class Product implements Searchable {
    private final UUID id;
    private final String productName;

    public Product(UUID id, String productName) {
        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Название продукта не может быть пустой строкой или null");
        }
        this.id = id;
        this.productName = productName;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public abstract boolean isSpecial();

    public abstract int getProductPrice();

    @Override
    @JsonIgnore
    public String getSearchTerm() {
        return productName;
    }

    @Override
    @JsonIgnore
    public String getContentType() {
        return "PRODUCT";
    }

    @Override
    public String getName() {
        return productName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productName.equals(product.productName);
    }

    @Override
    public int hashCode() {
        return productName.hashCode();
    }
}
