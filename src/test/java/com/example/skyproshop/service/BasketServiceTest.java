package com.example.skyproshop.service;


import com.example.skyproshop.exceptions.NoSuchProductException;
import com.example.skyproshop.model.basket.BasketItem;
import com.example.skyproshop.model.basket.ProductBasket;
import com.example.skyproshop.model.basket.UserBasket;
import com.example.skyproshop.model.product.Product;
import com.example.skyproshop.model.product.SimpleProduct;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


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
        when(storageService.getProductById(testId)).thenReturn(Optional.empty());
        assertThrows(NoSuchProductException.class, () -> {
            basketService.addProductToBasket(testId);
        });
    }

    @Test
    public void testAddExistingProductToBasket() {
        UUID validId = UUID.randomUUID();
        SimpleProduct testProduct = new SimpleProduct(validId, "Test Product", 1000);
        when(storageService.getProductById(validId)).thenReturn(Optional.of(testProduct));
        basketService.addProductToBasket(validId);
        verify(productBasket, times(1)).addProduct(validId);
    }

    @Test
    public void testGetEmptyUserBasket() {
        Map<UUID, Integer> emptyItems = new HashMap<>();
        when(productBasket.getAllProducts()).thenReturn(emptyItems);
        UserBasket userBasket = basketService.getUserBasket();
        assertThat(userBasket.getItems()).isEmpty();
        assertThat(userBasket.getTotal()).isZero();
    }

    @Test
    public void testGetUserBasketWithProducts() {
        UUID firstProductId = UUID.randomUUID();
        UUID secondProductId = UUID.randomUUID();

        Product firstProduct = new SimpleProduct(firstProductId, "Product 1", 1000);
        Product secondProduct = new SimpleProduct(secondProductId, "Product 2", 2000);

        Map<UUID, Integer> basketProducts = new HashMap<>();
        basketProducts.put(firstProductId, 3);
        basketProducts.put(secondProductId, 1);

        when(productBasket.getAllProducts()).thenReturn(basketProducts);
        when(storageService.getProductById(firstProductId)).thenReturn(Optional.of(firstProduct));
        when(storageService.getProductById(secondProductId)).thenReturn(Optional.of(secondProduct));

        UserBasket userBasket = basketService.getUserBasket();

        assertThat(userBasket.getItems().size()).isEqualTo(2);

        List<BasketItem> items = userBasket.getItems();

        BasketItem firstItem = items.get(0);
        assertThat(firstItem.getProduct()).isEqualTo(firstProduct);
        assertThat(firstItem.getQuantity()).isEqualTo(3);

        BasketItem secondItem = items.get(1);
        assertThat(secondItem.getProduct()).isEqualTo(secondProduct);
        assertThat(secondItem.getQuantity()).isEqualTo(1);

        int total = firstItem.getProduct().getProductPrice() * firstItem.getQuantity() +
                secondItem.getProduct().getProductPrice() * secondItem.getQuantity();

        assertThat(userBasket.getTotal()).isEqualTo(total);
    }
}
