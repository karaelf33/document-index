package com.example.documentindex.documents.factory;

import com.example.documentindex.dto.request.*;

public interface DocumentFactoryManager {
    void createDocument(DocumentRequest documentRequest);
    String getFileExtension(String fileName);
}
