package com.example.documentindex.controller;

import com.example.documentindex.dto.request.DocumentRequest;
import com.example.documentindex.dto.request.SearchRequest;
import com.example.documentindex.dto.response.DocumentResponse;
import com.example.documentindex.dto.response.SearchResponse;
import com.example.documentindex.service.DocumentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(MockitoExtension.class)
class DocumentControllerTest {

    private static final String QUERY_1 = "query1";
    private static final String QUERY_2 = "query2";
    private static final String QUERY_3= "query3";
    private static final String documentContent = "documentContent";
    private static final String file1 = "file1";
    private static final String file2 = "file2";
    private static final String content1 = "content1";
    private static final String content2 = "content2";
    private static final String content3 = "content3";

    @Mock
    DocumentService documentService;

    @InjectMocks
    private DocumentController documentController;


    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(documentController).build();
    }

    @Test
    void when_searchDocumentWith_searchRequestList_then_should_return_searchResponse() throws Exception {

        //given
        var searchRequestList = Arrays.asList(
                new SearchRequest(QUERY_1, "text1.txt"),
                new SearchRequest(QUERY_2, "text2.txt"),
                new SearchRequest(QUERY_3, "text3.txt"));

        var expectedSearchResponseList = Arrays.asList(
                new SearchResponse("text1.txt", 50.00),
                new SearchResponse("text2.txt", 100.00),
                new SearchResponse("text3.txt", 75.00));


        // when
        when(documentService.searchQueryInDocuments(searchRequestList)).thenReturn(expectedSearchResponseList);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/documents")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(searchRequestList)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedSearchResponseList)));

        verify(documentService, times(1)).searchQueryInDocuments(searchRequestList);
    }

    @Test
    public void when_saveDocumentWithContent_then_should_return_documentResponseList() throws Exception {

        //given
        var documentRequests = Arrays.asList(
                new DocumentRequest(QUERY_1, content1),
                new DocumentRequest(QUERY_2, content2),
                new DocumentRequest(QUERY_3, content3)
        );

        var expectedDocumentResponseList = Arrays.asList(
                new DocumentResponse("file message 1", "content message 3"),
                new DocumentResponse("file message 2", "content message 2"),
                new DocumentResponse("file message 3", "content message 1")
        );


        // when
        when(documentService.saveDocumentWithContent(documentRequests)).thenReturn(expectedDocumentResponseList);

        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/documents")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(documentRequests)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedDocumentResponseList)));

        verify(documentService, times(1)).saveDocumentWithContent(documentRequests);
    }
}