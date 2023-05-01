package com.example.documentindex.search;

public interface SearchService {
    double getQueryMatchScoreInContent(String query, String content);
}
