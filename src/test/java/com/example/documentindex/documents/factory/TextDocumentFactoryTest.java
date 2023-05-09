package com.example.documentindex.documents.factory;

import com.example.documentindex.dto.request.DocumentRequest;
import com.example.documentindex.dto.response.DocumentResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Files;

import static com.example.documentindex.util.Constants.*;
import static com.example.documentindex.util.ErrorMessage.CONTENT_ADDITION_ERROR;
import static com.example.documentindex.util.ErrorMessage.FILE_ERROR_CREATION;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TextDocumentFactoryTest {

    private static final String FILENAME = "fileName1";
    private static final String FILE_CONTENT = "content1";

    private static AutoCloseable mocks;
    @InjectMocks
    TextDocumentFactory textDocumentFactory;

    @BeforeEach
    void beforeEach() {
        mocks = Mockito.mockStatic(Files.class);
    }

    @AfterEach
    void afterEach() throws Exception {
        mocks.close();
    }

    @Test
    void should_return_documentResponse_when_call_saveDocumentWithContent_success_and_fileExist() {
        // given
        var documentRequest = new DocumentRequest(FILENAME, FILE_CONTENT);
        var expectedDocumentResponse = new DocumentResponse(FILE_EXIST, CONTENT_ADDED);

        when(Files.exists(any())).thenReturn(true);

        var actualDocumentResponseList = textDocumentFactory.saveDocumentWithContent(documentRequest);

        assertEquals(expectedDocumentResponse, actualDocumentResponseList);
    }

    @Test
    void should_return_documentResponse_when_call_saveDocumentWithContent_success_and_fileDoesNotExist() {

        var documentRequest = new DocumentRequest(FILENAME, FILE_CONTENT);
        var expectedDocumentResponse = new DocumentResponse(FILENAME + FILE_CREATED, CONTENT_ADDED);

        when(Files.exists(any())).thenReturn(false);
        var actualDocumentResponseList = textDocumentFactory.saveDocumentWithContent(documentRequest);

        assertEquals(expectedDocumentResponse, actualDocumentResponseList);
    }

    @Test
     void should_return_error_message_when_file_creation_fails() throws IOException {
        var documentRequest = new DocumentRequest(FILENAME, FILE_CONTENT);
        var expectedDocumentResponse = new DocumentResponse(FILE_ERROR_CREATION + FILENAME, CONTENT_ADDITION_ERROR);


        when(Files.createDirectories(any())).thenThrow(new IOException());
        var actualDocumentResponseList = textDocumentFactory.saveDocumentWithContent(documentRequest);

        assertEquals(expectedDocumentResponse, actualDocumentResponseList);
    }

}