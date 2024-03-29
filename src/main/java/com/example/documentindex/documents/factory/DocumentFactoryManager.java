package com.example.documentindex.documents.factory;

import com.example.documentindex.dto.request.DocumentRequest;
import com.example.documentindex.dto.request.SearchRequest;
import com.example.documentindex.dto.response.DocumentResponse;

public interface DocumentFactoryManager {
    String getContentFromDocument(SearchRequest searchRequest);

    DocumentResponse saveDocumentWithContent(DocumentRequest documentRequest);

    DocumentFactory getDocumentFactory(String documentRequest);

}
