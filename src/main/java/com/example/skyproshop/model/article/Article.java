package com.example.skyproshop.model.article;

import com.example.skyproshop.model.search.Searchable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public final class Article implements Searchable {
    private final UUID id;
    private final String articleTitle;
    private final String articleText;

    public Article(UUID id, String articleTitle, String articleText) {
        this.id = id;
        this.articleTitle = articleTitle;
        this.articleText = articleText;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return articleTitle + "\n" + articleText;
    }

    @Override
    @JsonIgnore
    public String getSearchTerm() {
        return toString();
    }

    @Override
    @JsonIgnore
    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public String getName() {
        return articleTitle;
    }

    @Override
    public String getStringRepresentation() {
        return getName() + " — " + getContentType();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return articleTitle.equals(article.articleTitle);
    }

    @Override
    public int hashCode() {
        return articleTitle.hashCode();
    }
}