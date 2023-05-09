package com.example.documentindex.documents.factory;

import com.example.documentindex.dto.request.DocumentRequest;
import com.example.documentindex.dto.request.SearchRequest;
import com.example.documentindex.dto.response.DocumentResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DocumentFactoryManagerImplTest {

    @Spy
    DocumentFactoryManagerImpl documentFactoryManager;

    static Stream<Arguments> documentFactory_implementations() {
        return Stream.of(Arguments.of("fileName.txt", "content", mock(TextDocumentFactory.class)),
                Arguments.of("fileName.docx", "content", mock(WordDocumentFactory.class)));
    }

    @BeforeEach
    void setUp() {

    }

    @ParameterizedTest
    @MethodSource("documentFactory_implementations")
    void getContentFromDocument_should_return_fileContent_when_FileExist(String fileName,
                                                                                String content,
                                                                                DocumentFactory documentFactory) {
        var searchRequest = new SearchRequest("query", fileName);
        when(documentFactoryManager.getDocumentFactory(fileName)).thenReturn(documentFactory);
        when(documentFactory.readFile(fileName)).thenReturn(content);

        String actualContentFromDocument = documentFactoryManager.getContentFromDocument(searchRequest);

        assertEquals(content,actualContentFromDocument);

    }

    @ParameterizedTest
    @MethodSource("documentFactory_implementations")
    void saveDocumentWithContent_when_RelatedFactoryExist(String fileName,
                                                                 String testContent,
                                                                 DocumentFactory factory) {
        var documentRequest = new DocumentRequest(fileName, testContent);
        var expectedDocumentResponse = new DocumentResponse(
                "Test Message for file", "Test message for content");

        when(documentFactoryManager.getDocumentFactory(fileName)).thenReturn(factory);
        when(factory.saveDocumentWithContent(documentRequest)).thenReturn(expectedDocumentResponse);


        DocumentResponse actualDocumentResponse = documentFactoryManager.saveDocumentWithContent(documentRequest);

        assertEquals(expectedDocumentResponse, actualDocumentResponse);


    }

    @Test
    void shouldReturnException_when_In_saveDocumentWithContent_FactoryNotValid() {
        DocumentFactory textDocumentFactory = Mockito.mock(TextDocumentFactory.class);
        DocumentFactory wordtDocumentFactory = Mockito.mock(WordDocumentFactory.class);
        var documentRequest = new DocumentRequest("test.KKK", "Test content");

        Assertions.assertThrows(IllegalArgumentException.class, () -> documentFactoryManager.saveDocumentWithContent(documentRequest));
        Assertions.assertThrows(IllegalArgumentException.class, () -> documentFactoryManager.saveDocumentWithContent(documentRequest));

        Mockito.verify(textDocumentFactory, Mockito.never()).saveDocumentWithContent(documentRequest);
        Mockito.verify(wordtDocumentFactory, Mockito.never()).saveDocumentWithContent(documentRequest);
    }

    @Test
    void getDocumentFactory_when_SupportedFileExtension() {
        DocumentFactory factory = documentFactoryManager.getDocumentFactory("SupportedFileExtensionName.txt");
        Assertions.assertTrue(factory instanceof TextDocumentFactory);

        factory = documentFactoryManager.getDocumentFactory("test.docx");
        Assertions.assertTrue(factory instanceof WordDocumentFactory);
    }

    @Test
    void getDocumentFactor_when_UnsupportedFileExtension() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> documentFactoryManager.getDocumentFactory("UNSupportedFileExtensionName.kkk"));
    }

    @Test
     void getDocumentFactory_when_NoFileExtension() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> documentFactoryManager.getDocumentFactory("NOFileExtensionName"));
    }

    @Test
    void getFileExtension_when_FileName_valid() {
        String validFileName = "filename_with_extension.txt";
        String expected = "txt";

        String actual = documentFactoryManager.getFileExtension(validFileName);

        assertEquals(expected, actual);
    }

    @Test
    void getFileExtension_When_FileName_Invalid() {
        String invalidFileName = "filename_without_extension";
        Assertions.assertThrows(IllegalArgumentException.class, () -> documentFactoryManager.getFileExtension(invalidFileName));
    }
}