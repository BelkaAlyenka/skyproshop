package com.example.skyproshop.service;

import com.example.skyproshop.model.product.DiscountedProduct;
import com.example.skyproshop.model.product.FixPriceProduct;
import com.example.skyproshop.model.product.Product;
import com.example.skyproshop.model.product.SimpleProduct;
import com.example.skyproshop.model.search.SearchResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {
    @Mock
    private StorageService storageService;
    @InjectMocks
    private SearchService searchService;

    @Test
    void testSearchEmptyStorage() {
        when(storageService.getAllSearchable()).thenReturn(Collections.emptyList());
        List<SearchResult> results = searchService.search("клавиатура");
        Assertions.assertTrue(results.isEmpty());
    }

    @Test
    void testSearchWithMatch() {
        Product testProduct = new SimpleProduct(UUID.randomUUID(), "смартфон", 13500);
        when(storageService.getAllSearchable()).thenReturn(Collections.singletonList(testProduct));
        List<SearchResult> results = searchService.search("смартфон");
        assertEquals(1, results.size());
        assertEquals("смартфон", results.get(0).getName());
    }

    @Test
    void testSearchNoMatch() {
        Product firstTestProduct = new FixPriceProduct(UUID.randomUUID(), "наушники");
        Product secondTestProduct = new DiscountedProduct(UUID.randomUUID(), "весы", 600, 20);
        when(storageService.getAllSearchable()).thenReturn(Arrays.asList(firstTestProduct, secondTestProduct));
        List<SearchResult> results = searchService.search("телевизор");
        Assertions.assertTrue(results.isEmpty());
    }
}
