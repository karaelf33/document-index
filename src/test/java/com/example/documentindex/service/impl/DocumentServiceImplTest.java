package com.example.documentindex.service.impl;

import com.example.documentindex.documents.factory.DocumentFactoryManager;
import com.example.documentindex.dto.request.DocumentRequest;
import com.example.documentindex.dto.request.SearchRequest;
import com.example.documentindex.dto.response.DocumentResponse;
import com.example.documentindex.dto.response.SearchResponse;
import com.example.documentindex.search.SearchService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DocumentServiceImplTest {

    @Mock
    SearchService searchService;
    @Mock
    DocumentFactoryManager documentFactoryManager;

    @InjectMocks
    DocumentServiceImpl documentService;


    @Test
    public void when_searchQueryInDocument_should_return_responseList() {
        List<SearchRequest> searchRequestList = Arrays.asList(
                new SearchRequest("query1", "file1"),
                new SearchRequest("query2", "file2"));

        List<SearchResponse> expectedSearchResponseList = Arrays.asList(
                new SearchResponse("file1", 0.5),
                new SearchResponse("file2", 0.3));
        String documentContent = "documentContent";

        //when
        when(documentFactoryManager.documentContent(any(SearchRequest.class)))
                .thenReturn(documentContent);
        when(searchService.getQueryMatchScoreInContent(anyString(), anyString()))
                .thenReturn(0.5)
                .thenReturn(0.3);
        List<SearchResponse> actualSearchResponseList = documentService.searchQueryInDocuments(searchRequestList);


        assertEquals(expectedSearchResponseList, actualSearchResponseList);
    }

    @Test
    public void when_saveDocumentWithContent_should_return_documentRequests() {
        List<DocumentRequest> documentRequests = Arrays.asList(
                new DocumentRequest("file1", "content1"),
                new DocumentRequest("file2", "content2")
        );
        DocumentResponse documentSaved1 = new DocumentResponse("file message 2", "content message 2");
        DocumentResponse documentSaved2 = new DocumentResponse("file message 1", "content message 1");
        List<DocumentResponse> expectedDocumentResponseList = Arrays.asList(
                documentSaved2,
                documentSaved1);

        when(documentFactoryManager.saveDocumentWithContent(any(DocumentRequest.class)))
                .thenReturn(documentSaved2)
                .thenReturn(documentSaved1);

        List<DocumentResponse> acutalDocumentResponseList = documentService.saveDocumentWithContent(documentRequests);

        assertEquals(expectedDocumentResponseList, acutalDocumentResponseList);

    }

}