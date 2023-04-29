package com.example.documentindex.service;

import com.example.documentindex.dto.request.*;
import com.example.documentindex.dto.response.*;

import java.util.*;

public interface DocumentService {
    List<SearchResponse> searchStringInDocument(List<SearchRequest> searchRequestList);

    List<SearchResponse> saveDocumentWithContent(List<DocumentRequest> documentRequests);
}
