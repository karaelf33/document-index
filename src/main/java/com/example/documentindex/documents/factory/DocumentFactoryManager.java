package com.example.documentindex.documents.factory;

import com.example.documentindex.dto.request.*;
import com.example.documentindex.dto.response.DocumentResponse;

public interface DocumentFactoryManager {
    DocumentResponse saveDocumentWithContent(DocumentRequest documentRequest);
    String getFileExtension(String fileName);
}
