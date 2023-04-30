package com.example.documentindex.documents.factory;

import com.example.documentindex.dto.request.DocumentRequest;
import com.example.documentindex.dto.request.SearchRequest;
import com.example.documentindex.dto.response.DocumentResponse;
import com.example.documentindex.dto.response.SearchResponse;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
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
    public SearchResponse searchQueryInDocument(SearchRequest searchRequest){
        String fileExtension = getFileExtension(searchRequest.fileName());
        DocumentFactory factory = FACTORIES.get(fileExtension);

        if (factory == null) {
            throw new IllegalArgumentException("Unsupported file extension: " + fileExtension);
        }
        return searchQueryInDocument2(searchRequest);


    }

    public SearchResponse searchQueryInDocument2(SearchRequest searchRequest) {
        String fileContent = readFile2(searchRequest.fileName());
        String[] f=fileContent.split(" ");
        System.out.println(Arrays.toString(f));

        return null;
    }
    @Override
    public String readFile(String filename) {
        ClassPathResource resource = new ClassPathResource("documents/" + filename);
        try (InputStream inputStream = resource.getInputStream()) {
            byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Error reading file: " + filename + " - " + e.getMessage());
            return null;
        }
    }
    //@Override
    public String readFile2(String filename) {
        ClassPathResource resource = new ClassPathResource("documents/" + filename);
        String extension = filename.substring(filename.lastIndexOf('.') + 1);
        try (InputStream inputStream = resource.getInputStream()) {
            if ("docx".equalsIgnoreCase(extension)) {
                XWPFDocument document = new XWPFDocument(inputStream);
                XWPFWordExtractor extractor = new XWPFWordExtractor(document);
                return extractor.getText();
            } else {
                byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);
                return new String(bytes, StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + filename + " - " + e.getMessage());
            return null;
        }
    }

}