package com.example.documentindex.documents.factory;


import com.example.documentindex.dto.request.DocumentRequest;
import com.example.documentindex.dto.response.DocumentResponse;

public interface DocumentFactory {
    DocumentResponse saveDocumentWithContent(DocumentRequest documentRequest);
    String readFile(String filename);


}
