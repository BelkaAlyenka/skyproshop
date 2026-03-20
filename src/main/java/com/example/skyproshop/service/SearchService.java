package com.example.skyproshop.service;

import com.example.skyproshop.model.search.SearchResult;
import com.example.skyproshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public List<SearchResult> search(String request) {
        Collection<Searchable> allSearchable = storageService.getAllSearchable();
        return allSearchable.stream()
                .filter(searchable -> searchable.getName().contains(request))
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toList());
    }
}
