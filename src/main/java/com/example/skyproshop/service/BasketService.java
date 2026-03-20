package com.example.skyproshop.service;

import com.example.skyproshop.exceptions.NoSuchProductException;
import com.example.skyproshop.model.basket.BasketItem;
import com.example.skyproshop.model.basket.ProductBasket;
import com.example.skyproshop.model.basket.UserBasket;
import com.example.skyproshop.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public UserBasket getUserBasket() {
        Map<UUID, Integer> mapProducts = productBasket.getAllProducts();

        List<BasketItem> basketItems = mapProducts.entrySet().stream()
                .map(entry -> {
                    Product product = storageService.getProductById(entry.getKey())
                            .orElseThrow(NoSuchProductException::new);
                    return new BasketItem(product, entry.getValue());
                })
                .collect(Collectors.toList());

        return new UserBasket(basketItems);
    }

    public void addProductToBasket(UUID id) {
        storageService.getProductById(id)
                .orElseThrow(NoSuchProductException::new);

        productBasket.addProduct(id);
    }
}
