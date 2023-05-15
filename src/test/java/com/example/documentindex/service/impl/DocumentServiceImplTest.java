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
    private static final String QUERY_1 = "query1";
    private static final String QUERY_2 = "query2";
    private static final String documentContent = "documentContent";
    private static final String file1 = "file1";
    private static final String file2 = "file2";
    private static final String content1 = "content1";
    private static final String content2 = "content2";
    @Mock
    SearchService searchService;
    @Mock
    DocumentFactoryManager documentFactoryManager;

    @InjectMocks
    DocumentServiceImpl documentService;


    @Test
    void when_searchQueryInDocument_should_return_responseList() {

        var searchRequestList = Arrays.asList(
                new SearchRequest(QUERY_1, file1),
                new SearchRequest(QUERY_2, file2));

        var expectedSearchResponseList = Arrays.asList(
                new SearchResponse(file1, 0.5),
                new SearchResponse(file2, 0.3));


        //when
        when(documentFactoryManager.getContentFromDocument(any(SearchRequest.class)))
                .thenReturn(documentContent);
        when(searchService.calculateQueryMatchScoreInContent(anyString(), anyString()))
                .thenReturn(0.5)
                .thenReturn(0.3);
        List<SearchResponse> actualSearchResponseList = documentService.searchQueryInDocuments(searchRequestList);


        assertEquals(expectedSearchResponseList, actualSearchResponseList);
    }

    @Test
    void when_saveDocumentWithContent_should_return_documentRequests() {
        var documentRequests = Arrays.asList(
                new DocumentRequest(file1, content1),
                new DocumentRequest(file2, content2)
        );
        var documentSaved1 = new DocumentResponse("file message 2", "content message 2");
        var documentSaved2 = new DocumentResponse("file message 1", "content message 1");
        var expectedDocumentResponseList = Arrays.asList(
                documentSaved2,
                documentSaved1);

        when(documentFactoryManager.saveDocumentWithContent(any(DocumentRequest.class)))
                .thenReturn(documentSaved2)
                .thenReturn(documentSaved1);

        var acutalDocumentResponseList = documentService.saveDocumentWithContent(documentRequests);

        assertEquals(expectedDocumentResponseList, acutalDocumentResponseList);

    }

}