package com.example.documentindex.documents.factory;

import com.example.documentindex.dto.request.DocumentRequest;
import com.example.documentindex.dto.request.SearchRequest;
import com.example.documentindex.dto.response.DocumentResponse;
import com.example.documentindex.dto.response.SearchResponse;

public interface DocumentFactoryManager {
    DocumentResponse saveDocumentWithContent(DocumentRequest documentRequest);
    String getFileExtension(String fileName);
    SearchResponse searchQueryInDocument(SearchRequest searchRequest);
    String readFile(String filename);
}
