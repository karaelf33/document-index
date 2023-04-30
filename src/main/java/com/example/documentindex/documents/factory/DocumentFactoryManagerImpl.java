package com.example.documentindex.documents.factory;

import com.example.documentindex.dto.request.DocumentRequest;
import com.example.documentindex.dto.request.SearchRequest;
import com.example.documentindex.dto.response.DocumentResponse;
import com.example.documentindex.dto.response.SearchResponse;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@Service
public class DocumentFactoryManagerImpl implements DocumentFactoryManager {
    private static final Map<String, DocumentFactory> FACTORIES = new HashMap<>();

    static {
        FACTORIES.put("txt", new TextDocumentFactory());
        FACTORIES.put("docx", new WordDocumentFactory());
    }

    @Override
    public DocumentResponse saveDocumentWithContent(DocumentRequest documentRequest) {
        String fileExtension = getFileExtension(documentRequest.fileName());
        DocumentFactory factory = FACTORIES.get(fileExtension);
        if (factory == null) {
            throw new IllegalArgumentException("Unsupported file extension: " + fileExtension);
        }
        return factory.saveDocumentWithContent(documentRequest);
    }

    @Override
    public String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1) {
            throw new IllegalArgumentException("Invalid file name: " + fileName);
        }
        return fileName.substring(dotIndex + 1);
    }

    @Override
    public String documentContent(SearchRequest searchRequest) {
        String fileExtension = getFileExtension(searchRequest.fileName());
        DocumentFactory factory = FACTORIES.get(fileExtension);

        if (factory == null) {
            throw new IllegalArgumentException("Unsupported file extension: " + fileExtension);
        }

        return factory.readFile(searchRequest.fileName());
    }

}