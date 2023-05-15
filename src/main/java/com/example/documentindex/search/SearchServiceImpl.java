package com.example.documentindex.search;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class SearchServiceImpl  implements SearchService{

    @Override
    public double calculateQueryMatchScoreInContent(String query, String content) {
        if (query.isEmpty() || content.isEmpty()){
            return 0.00;
        }
        String lowerCaseQuery = query.toLowerCase();
        String lowerCaseContent = content.toLowerCase();
        String[] queryWords = lowerCaseQuery.trim().split("\\s+"); // split the string by spaces and double spaces
        String[] contentWords = lowerCaseContent.trim().split("\\s+"); // split the string by spaces and double spaces

        int queryPointer;
        int matches;
        int maxMatchWords = 0;
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
