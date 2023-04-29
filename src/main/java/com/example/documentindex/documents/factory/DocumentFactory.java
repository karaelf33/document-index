package com.example.documentindex.documents.factory;


import com.example.documentindex.dto.request.*;
import com.example.documentindex.dto.response.DocumentResponse;

public interface DocumentFactory {
    DocumentResponse saveDocumentWithContent(DocumentRequest documentRequest);

}
