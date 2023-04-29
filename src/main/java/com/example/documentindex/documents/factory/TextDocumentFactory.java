package com.example.documentindex.documents.factory;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import com.example.documentindex.dto.request.DocumentRequest;
import com.example.documentindex.dto.response.DocumentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.example.documentindex.util.Constants.*;
import static com.example.documentindex.util.ErrorMessage.CONTENT_ADDITION_ERROR;
import static com.example.documentindex.util.ErrorMessage.FILE_ERROR_CREATION;

public class TextDocumentFactory implements DocumentFactory {

    Logger logger = LoggerFactory.getLogger(TextDocumentFactory.class);

    @Override
    public DocumentResponse saveDocumentWithContent(DocumentRequest documentRequest) {
        String fileMessage;
        String contentMessage;
        String fileName = documentRequest.getFileName();

        try {
            // create the directory if it doesn't exist
            Files.createDirectories(Paths.get(RESOURCES_PATH));

            // create or append content to the file
            Path path = Paths.get(RESOURCES_PATH + FILE_SEPARATOR + fileName);
            fileMessage = Files.exists(path) ? FILE_EXIST : fileName + FILE_CREATED;

            Files.write(path,
                    documentRequest.getContent().getBytes(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);

            contentMessage = CONTENT_ADDED;
            logger.info(fileMessage, contentMessage);
        } catch (IOException e) {
            fileMessage = FILE_ERROR_CREATION + fileName;
            contentMessage = CONTENT_ADDITION_ERROR;
            logger.error(fileMessage, contentMessage, e.getMessage());
        }
        return new DocumentResponse(fileMessage, contentMessage);
    }
}
