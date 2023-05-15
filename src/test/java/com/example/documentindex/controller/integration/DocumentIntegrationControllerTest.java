package com.example.documentindex.controller.integration;

import com.example.documentindex.dto.request.SearchRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class DocumentIntegrationControllerTest {
   private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void when_search_documents_successfully() throws Exception {
        List<SearchRequest> searchRequestList = new ArrayList<>();
        searchRequestList.add(new SearchRequest("This is NOT_INCLUDE", "document1.txt"));
        searchRequestList.add(new SearchRequest("the content of document NOT_INCLUDE NOT_INCLUDE NOT_INCLUDE", "document2.txt"));
        String requestBody = objectMapper.writeValueAsString(searchRequestList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/documents")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].fileName").value("document1.txt"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].fileName").value("document2.txt"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].matchRate").value(66.66666666666666))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].matchRate").value(57.14285714285714));

    }


}