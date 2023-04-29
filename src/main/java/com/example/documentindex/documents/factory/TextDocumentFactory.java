package com.example.documentindex.documents.factory;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import com.example.documentindex.dto.request.DocumentRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static com.example.documentindex.Constants.RESOURCES_PATH;

public class TextDocumentFactory implements DocumentFactory {

    Logger logger = LoggerFactory.getLogger(TextDocumentFactory.class);

    @Override
    public void saveDocumentWithContent(DocumentRequest documentRequest) {
        try {
            // create the directory if it doesn't exist
            Files.createDirectories(Paths.get(RESOURCES_PATH));

            // create or append content to the file
            Files.write(Paths.get(RESOURCES_PATH + "/" + documentRequest.getFileName()),
                    documentRequest.getContent().getBytes(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);

            logger.info("Content added successfully for {}", documentRequest.getFileName());
        } catch (IOException e) {
            logger.error("Error creating or writing to file:{} ", e.getMessage());
        }
    }
}
