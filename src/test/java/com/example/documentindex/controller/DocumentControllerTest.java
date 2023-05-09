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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(MockitoExtension.class)
class DocumentControllerTest {

    private static final String QUERY_1 = "query1";
    private static final String QUERY_2 = "query2";
    private static final String QUERY_3 = "query3";
    private static final String FILE_NAME_1 = "file1";
    private static final String FILE_NAME_2 = "file2";
    private static final String FILE_NAME_3 = "file3";
    private static final String CONTENT_MESSAGE_1 = "CONTENT_MESSAGE_1";
    private static final String CONTENT_MESSAGE_2 = "CONTENT_MESSAGE_2";
    private static final String CONTENT_MESSAGE_3 = "CONTENT_MESSAGE_3";

    private static final String FILE_MESSAGE_1 = "file message 1";
    private static final String FILE_MESSAGE_2 = "file message 2";
    private static final String FILE_MESSAGE_3 = "file message 3";

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
                new SearchRequest(QUERY_1, FILE_NAME_1),
                new SearchRequest(QUERY_2, FILE_NAME_2),
                new SearchRequest(QUERY_3, FILE_NAME_3));

        var expectedSearchResponseList = Arrays.asList(
                new SearchResponse(FILE_NAME_1, 50.00),
                new SearchResponse(FILE_NAME_2, 100.00),
                new SearchResponse(FILE_NAME_3, 75.00));


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
    void when_saveDocumentWithContent_then_should_return_documentResponseList() throws Exception {

        //given
        var documentRequests = Arrays.asList(
                new DocumentRequest(FILE_NAME_1, CONTENT_MESSAGE_1),
                new DocumentRequest(FILE_NAME_2, CONTENT_MESSAGE_2),
                new DocumentRequest(FILE_NAME_3, CONTENT_MESSAGE_3)
        );


        var expectedDocumentResponseList = Arrays.asList(
                new DocumentResponse(FILE_MESSAGE_1, CONTENT_MESSAGE_1),
                new DocumentResponse(FILE_MESSAGE_2, CONTENT_MESSAGE_2),
                new DocumentResponse(FILE_MESSAGE_3, CONTENT_MESSAGE_3)
        );


        // when
        when(documentService.saveDocumentWithContent(documentRequests)).thenReturn(expectedDocumentResponseList);

        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/documents")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(documentRequests)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedDocumentResponseList)));

        verify(documentService, times(1)).saveDocumentWithContent(documentRequests);
    }
}