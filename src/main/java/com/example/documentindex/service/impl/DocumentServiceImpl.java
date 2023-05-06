package com.example.documentindex.service.impl;

import com.example.documentindex.documents.factory.DocumentFactoryManager;
import com.example.documentindex.dto.request.DocumentRequest;
import com.example.documentindex.dto.request.SearchRequest;
import com.example.documentindex.dto.response.DocumentResponse;
import com.example.documentindex.dto.response.SearchResponse;
import com.example.documentindex.search.SearchService;
import com.example.documentindex.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final SearchService searchService;
    private final DocumentFactoryManager documentFactoryManager;

    @Override
    public List<SearchResponse> searchQueryInDocuments(List<SearchRequest> searchRequestList) {
        List<SearchResponse> searchResponseList = new ArrayList<>();

        for (var searchRequest : searchRequestList) {
            String documentContent = documentFactoryManager.getContentFromDocument(searchRequest);
            double queryScoreInContent = searchService.getQueryMatchScoreInContent(searchRequest.query(), documentContent);
            var searchResponse = new SearchResponse(searchRequest.fileName(), queryScoreInContent);
            searchResponseList.add(searchResponse);
        }
        return searchResponseList;
    }

    @Override
    public List<DocumentResponse> saveDocumentWithContent(List<DocumentRequest> documentRequests) {
        List<DocumentResponse> responseList = new ArrayList<>();

        for (var documentRequest : documentRequests) {
            DocumentResponse documentSaved = documentFactoryManager.saveDocumentWithContent(documentRequest);
            responseList.add(documentSaved);
        }
        return responseList;
    }


}
