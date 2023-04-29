package com.example.documentindex.service;

import com.example.documentindex.dto.request.*;
import com.example.documentindex.dto.response.*;

import java.util.*;

public interface DocumentService {
    List<DocumentResponse> searchStringInDocument(List<SearchRequest> searchRequestList);

    List<DocumentResponse> saveDocumentWithContent(List<DocumentRequest> documentRequests);
}
