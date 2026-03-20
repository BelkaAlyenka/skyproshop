package com.example.skyproshop.service;

import com.example.skyproshop.model.article.Article;
import com.example.skyproshop.model.product.DiscountedProduct;
import com.example.skyproshop.model.product.FixPriceProduct;
import com.example.skyproshop.model.product.Product;
import com.example.skyproshop.model.product.SimpleProduct;
import com.example.skyproshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StorageService {
    private final Map<UUID, Product> products;
    private final Map<UUID, Article> articles;

    public StorageService() {
        this.products = new HashMap<>();
        this.articles = new HashMap<>();

        testProducts();
    }

    public Map<UUID, Product> getAllProducts() {
        return products;
    }

    public Map<UUID, Article> getAllArticles() {
        return articles;
    }

    public Collection<Searchable> getAllSearchable() {
        return Stream.of(
                        products.values().stream(),
                        articles.values().stream()
                )
                .flatMap(stream -> stream)
                .collect(Collectors.toList());
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(products.get(id));
    }

    private void testProducts() {
        UUID firstProductId = UUID.randomUUID();
        Product firstProduct = new SimpleProduct(firstProductId, "смартфон", 13500);
        products.put(firstProductId, firstProduct);

        UUID secondProductId = UUID.randomUUID();
        Product secondProduct = new FixPriceProduct(secondProductId, "наушники");
        products.put(secondProductId, secondProduct);

        UUID thirdProductId = UUID.randomUUID();
        Product thirdProduct = new DiscountedProduct(thirdProductId,"весы", 600, 20);
        products.put(thirdProductId, thirdProduct);

        UUID firstArticleId = UUID.randomUUID();
        Article firstArticle = new Article(firstArticleId, "про весы", "С помощью весов можно взвешивать не только твердые и сыпучие продукты, но и любые жидкости. Прибор умеет измерять объем воды или молока, а также обладает функцией тарирования");
        articles.put(firstArticleId, firstArticle);

        UUID secondArticleId = UUID.randomUUID();
        Article secondArticle = new Article(secondArticleId,"беспроводные наушники", "«Настоящие беспроводные» наушники — это Bluetooth-модели, которые работают без какого-либо провода между наушниками и источником звука (смартфоном, ноутбуком и так далее)");
        articles.put(secondArticleId, secondArticle);
    }
}
