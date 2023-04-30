package com.example.documentindex.service.impl;

import com.example.documentindex.documents.factory.DocumentFactoryManager;
import com.example.documentindex.dto.request.DocumentRequest;
import com.example.documentindex.dto.request.SearchRequest;
import com.example.documentindex.dto.response.DocumentResponse;
import com.example.documentindex.dto.response.SearchResponse;
import com.example.documentindex.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private final DocumentFactoryManager documentFactoryManager;

    public DocumentServiceImpl(DocumentFactoryManager documentFactoryManager) {
        this.documentFactoryManager = documentFactoryManager;
    }

    @Override
    public List<SearchResponse> searchQueryInDocuments(List<SearchRequest> searchRequestList) {
        for (SearchRequest s:searchRequestList){
            documentFactoryManager.searchQueryInDocument(s);
        }

        return null;
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
