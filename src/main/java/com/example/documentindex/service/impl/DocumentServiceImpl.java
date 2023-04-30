package com.example.documentindex.service.impl;

import com.example.documentindex.documents.factory.DocumentFactoryManager;
import com.example.documentindex.dto.request.DocumentRequest;
import com.example.documentindex.dto.request.SearchRequest;
import com.example.documentindex.dto.response.DocumentResponse;
import com.example.documentindex.dto.response.SearchResponse;
import com.example.documentindex.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private final DocumentFactoryManager documentFactoryManager;

    public DocumentServiceImpl(DocumentFactoryManager documentFactoryManager) {
        this.documentFactoryManager = documentFactoryManager;
    }

    @Override
    public List<SearchResponse> searchQueryInDocuments(List<SearchRequest> searchRequestList) {
        for (SearchRequest s : searchRequestList) {
            String content = documentFactoryManager.documentContent(s);
            System.out.println(getQueryScoreInContent(s.query(), content));;
        }

        return null;
    }


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

    @Override
    public List<DocumentResponse> saveDocumentWithContent(List<DocumentRequest> documentRequests) {
        List<DocumentResponse> responseList = new ArrayList<>();
        for (DocumentRequest documentRequest : documentRequests) {
            responseList.add(
                    documentFactoryManager.saveDocumentWithContent(documentRequest));
        }
        return responseList;
    }


}
