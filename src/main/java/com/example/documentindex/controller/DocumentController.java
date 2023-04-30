package com.example.documentindex.controller;


import com.example.documentindex.dto.request.DocumentRequest;
import com.example.documentindex.dto.request.SearchRequest;
import com.example.documentindex.dto.response.DocumentResponse;
import com.example.documentindex.dto.response.SearchResponse;
import com.example.documentindex.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@RestController
@RequestMapping("v1/api/documents")
public class DocumentController {

    @Autowired
   private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("")
    public List<SearchResponse> searchDocument(@RequestBody List<SearchRequest> searchRequestList){

        return documentService.searchQueryInDocuments(searchRequestList);
    }

    @PostMapping("")
    public List<DocumentResponse> saveDocumentWithContent(@RequestBody List<DocumentRequest> documentRequests){
        return documentService.saveDocumentWithContent(documentRequests);
    }


}
