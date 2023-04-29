package com.example.documentindex.controller;

import com.example.documentindex.dto.request.*;
import com.example.documentindex.dto.response.*;
import com.example.documentindex.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

        return documentService.searchStringInDocument(searchRequestList);
    }

    @PostMapping("")
    public List<SearchResponse> createFile(@RequestBody List<DocumentRequest> documentRequests){
        return documentService.createFiles(documentRequests);
    }


}
