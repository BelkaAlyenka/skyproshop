package com.example.skyproshop.service;

import com.example.skyproshop.exceptions.NoSuchProductException;
import com.example.skyproshop.model.basket.ProductBasket;
import com.example.skyproshop.model.product.SimpleProduct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {
    @Mock
    private ProductBasket productBasket;
    @Mock
    private StorageService storageService;
    @InjectMocks
    private BasketService basketService;

    @Test
    public void testAddAbsentProductToBasket() {
        UUID testId = UUID.randomUUID();
        Mockito.when(storageService.getProductById(testId)).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchProductException.class, () -> {
            basketService.addProductToBasket(testId);
        });
    }

    @Test
    void testAddExistingProductToBasket() {
        UUID validId = UUID.randomUUID();
        SimpleProduct testProduct = new SimpleProduct(validId, "Test Product", 1000);
        Mockito.when(this.storageService.getProductById(validId)).thenReturn(Optional.of(testProduct));
        this.basketService.addProductToBasket(validId);
    }
}
