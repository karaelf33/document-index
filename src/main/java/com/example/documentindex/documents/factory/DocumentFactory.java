package com.example.documentindex.documents.factory;


import com.example.documentindex.dto.request.*;

public interface DocumentFactory {
    void createDocument(DocumentRequest documentRequest);
    void writeContent(DocumentRequest documentRequest);

}
