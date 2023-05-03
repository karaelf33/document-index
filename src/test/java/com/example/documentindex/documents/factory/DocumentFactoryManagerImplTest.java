package com.example.documentindex.documents.factory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DocumentFactoryManagerImplTest {

    @InjectMocks
    DocumentFactoryManagerImpl documentFactoryManager;



    @Test
    public void getFileExtension_when_FileName_valid(){
        String validFileName = "filename_with_extension.txt";
        String expected="txt";

        String actual = documentFactoryManager.getFileExtension(validFileName);

        assertEquals(expected,actual);
    }
    @Test
    public void getFileExtension_When_FileName_Invalid(){
        String invalidFileName = "filename_without_extension";
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            documentFactoryManager.getFileExtension(invalidFileName);
        });
    }
}