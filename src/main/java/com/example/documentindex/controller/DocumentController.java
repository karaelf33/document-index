package com.example.documentindex.controller;


import com.example.documentindex.dto.request.DocumentRequest;
import com.example.documentindex.dto.request.SearchRequest;
import com.example.documentindex.dto.response.DocumentResponse;
import com.example.documentindex.dto.response.SearchResponse;
import com.example.documentindex.service.DocumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/documents")
public class DocumentController {

    private final DocumentService documentService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SearchResponse> searchDocument(@RequestBody List<SearchRequest> searchRequestList) {
        return documentService.searchQueryInDocuments(searchRequestList);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<DocumentResponse> saveDocumentWithContent(@RequestBody List<DocumentRequest> documentRequests) {
        return documentService.saveDocumentWithContent(documentRequests);
    }

}
