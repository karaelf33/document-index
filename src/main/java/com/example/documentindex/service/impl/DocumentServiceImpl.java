package com.example.documentindex.service.impl;

import com.example.documentindex.documents.factory.DocumentFactoryManager;
import com.example.documentindex.dto.request.DocumentRequest;
import com.example.documentindex.dto.request.SearchRequest;
import com.example.documentindex.dto.response.DocumentResponse;
import com.example.documentindex.dto.response.SearchResponse;
import com.example.documentindex.search.SearchService;
import com.example.documentindex.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class DocumentServiceImpl implements DocumentService {

    private final SearchService searchService;
    private final DocumentFactoryManager documentFactoryManager;

    public DocumentServiceImpl(SearchService searchService, DocumentFactoryManager documentFactoryManager) {
        this.searchService = searchService;
        this.documentFactoryManager = documentFactoryManager;
    }

    @Override
    public List<SearchResponse> searchQueryInDocuments(List<SearchRequest> searchRequestList) {
        List<SearchResponse> searchResponseList =new ArrayList<>();
        for (SearchRequest s : searchRequestList) {
            String content = documentFactoryManager.documentContent(s);
            double queryScoreInContent = searchService.getQueryScoreInContent(s.query(), content);
            SearchResponse searchResponse=new SearchResponse(s.fileName(),queryScoreInContent);
            searchResponseList.add(searchResponse);
        }

        return searchResponseList;
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
