package com.example.documentindex.controller;


import com.example.documentindex.dto.request.DocumentRequest;
import com.example.documentindex.dto.request.SearchRequest;
import com.example.documentindex.dto.response.DocumentResponse;
import com.example.documentindex.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<DocumentResponse> searchDocument(@RequestBody List<SearchRequest> searchRequestList){

        return documentService.searchStringInDocument(searchRequestList);
    }

    @PostMapping("")
    public List<DocumentResponse> saveDocumentWithContent(@RequestBody List<DocumentRequest> documentRequests){
        return documentService.saveDocumentWithContent(documentRequests);
    }


}
