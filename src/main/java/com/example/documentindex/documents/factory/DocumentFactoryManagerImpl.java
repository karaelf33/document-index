package com.example.documentindex.documents.factory;

import com.example.documentindex.dto.request.DocumentRequest;
import com.example.documentindex.dto.request.SearchRequest;
import com.example.documentindex.dto.response.DocumentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.example.documentindex.util.Constants.DOCX;
import static com.example.documentindex.util.Constants.TXT;
import static com.example.documentindex.util.ErrorMessage.INVALID_FILE_NAME;
import static com.example.documentindex.util.ErrorMessage.UNSUPPORTED_FILE_EXTENSION;

@Slf4j
@Service
public class DocumentFactoryManagerImpl implements DocumentFactoryManager {
    private static final Map<String, DocumentFactory> FACTORIES = new HashMap<>();

    static {
        FACTORIES.put(TXT, new TextDocumentFactory());
        FACTORIES.put(DOCX, new WordDocumentFactory());
    }


    @Override
    public String getContentFromDocument(SearchRequest searchRequest) {
        String fileName = searchRequest.fileName();
        DocumentFactory factory = getDocumentFactory(fileName);
        return factory.readFile(fileName);
    }

    @Override
    public DocumentResponse saveDocumentWithContent(DocumentRequest documentRequest) {
        DocumentFactory factory = getDocumentFactory(documentRequest.fileName());
        return factory.saveDocumentWithContent(documentRequest);
    }

    @Override
    public DocumentFactory getDocumentFactory(String fileName) {
        String fileExtension = getFileExtension(fileName);
        DocumentFactory factory = FACTORIES.get(fileExtension);
        if (factory == null) {
            log.error("File not found : {}",fileName);
            throw new IllegalArgumentException(UNSUPPORTED_FILE_EXTENSION + fileExtension);
        }
        log.info("Retrieving DocumentFactory for file extension: {}",fileExtension);
        return factory;
    }

    public String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1) {
            log.error("This file doesn't has file extension : {}",fileName);
            throw new IllegalArgumentException(INVALID_FILE_NAME + fileName);
        }
        log.info("Extract file name from file extension {}",fileName);
        return fileName.substring(dotIndex + 1);
    }

}
