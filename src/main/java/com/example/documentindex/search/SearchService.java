package com.example.documentindex.search;

public interface SearchService {
    double getQueryScoreInContent(String query, String content);
}
