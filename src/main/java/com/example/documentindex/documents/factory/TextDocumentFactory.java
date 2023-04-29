package com.example.documentindex.documents.factory;


import com.example.documentindex.dto.request.*;

import java.io.*;
import java.nio.file.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static com.example.documentindex.Constants.RESOURCES_PATH;

public class TextDocumentFactory implements DocumentFactory {

    Logger logger = LoggerFactory.getLogger(TextDocumentFactory.class);

    @Override
    public void createDocument(DocumentRequest documentRequest) {
        try {
            Files.createDirectories(Paths.get(RESOURCES_PATH));
            Files.createFile(Paths.get(RESOURCES_PATH + "/" + documentRequest.getFileName()));
            logger.info("Document created successfully.");
        } catch (IOException e) {
            logger.error("Error creating file: " + e.getMessage());
        }

    }

    @Override
    public void writeContent(DocumentRequest documentRequest) {
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
