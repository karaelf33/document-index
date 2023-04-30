package com.example.documentindex.search;

import java.util.Objects;

public class SearchServiceImpl  implements SearchService{

    @Override
    public double getQueryScoreInContent(String query, String content) {
        String[] queryWords = query.trim().split("\\s+"); // split the string by spaces and double spaces
        String[] contentWords = content.trim().split("\\s+"); // split the string by spaces and double spaces

        int queryPointer;
        int maxMatchWords = 0;
        int matches;
        int contentPointer = 0;

        while (contentPointer < contentWords.length - queryWords.length + 1) {
            matches = 0;
            queryPointer = 0;

            while (queryPointer < queryWords.length &&
                    Objects.equals(contentWords[contentPointer + queryPointer], queryWords[queryPointer])) {
                matches++;
                queryPointer++;
            }
            maxMatchWords = Math.max(matches, maxMatchWords);
            contentPointer++;
        }
        return (double) maxMatchWords / queryWords.length * 100;
    }
}
