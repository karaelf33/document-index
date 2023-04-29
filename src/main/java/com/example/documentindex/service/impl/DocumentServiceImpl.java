package com.example.documentindex.service.impl;

import com.example.documentindex.documents.factory.*;
import com.example.documentindex.dto.request.*;
import com.example.documentindex.dto.response.DocumentResponse;
import com.example.documentindex.service.DocumentService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private final DocumentFactoryManager documentFactoryManager;

    public DocumentServiceImpl(DocumentFactoryManager documentFactoryManager) {
        this.documentFactoryManager = documentFactoryManager;
    }

    @Override
    public List<DocumentResponse> searchStringInDocument(List<SearchRequest> searchRequestList) {

        File file = new File("../resources/documents/TxtFileExample1.txt");
        try {
            File myObj = new File("../resources/documents/TxtFileExample1.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<DocumentResponse> saveDocumentWithContent(List<DocumentRequest> documentRequests) {
        List<DocumentResponse> responseList = new ArrayList<>();
        for (DocumentRequest documentRequest : documentRequests) {
            responseList.add(
                    documentFactoryManager.saveDocumentWithContent(documentRequest));
        }
        return responseList;
    }


}
