package com.example.documentindex.search;

public interface SearchService {
    double calculateQueryMatchScoreInContent(String query, String content);
}
