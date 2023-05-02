package com.example.documentindex.documents.factory;


import com.example.documentindex.dto.request.DocumentRequest;
import com.example.documentindex.dto.response.DocumentResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static com.example.documentindex.util.Constants.CONTENT_ADDED;
import static com.example.documentindex.util.Constants.FILE_CREATED;
import static com.example.documentindex.util.Constants.RESOURCES_PATH;
import static com.example.documentindex.util.Constants.FILE_EXTENSION_SEPARATOR;
import static com.example.documentindex.util.Constants.FILE_SEPARATOR;
import static com.example.documentindex.util.Constants.FILE_EXIST;
import static com.example.documentindex.util.Constants.DOCUMENTS;
import static com.example.documentindex.util.ErrorMessage.*;

public class TextDocumentFactory implements DocumentFactory {

    private static final Logger logger =
            LogManager.getLogger(WordDocumentFactory.class);

    @Override
    public DocumentResponse saveDocumentWithContent(DocumentRequest documentRequest) {
        String fileMessage;
        String contentMessage;
        String fileName = documentRequest.fileName();

        try {
            // create the directory if it doesn't exist
            Files.createDirectories(Paths.get(RESOURCES_PATH));

            // create or append content to the file
            Path path = Paths.get(RESOURCES_PATH + FILE_SEPARATOR + fileName);
            fileMessage = Files.exists(path) ? FILE_EXIST : fileName + FILE_CREATED;

            Files.write(path,
                    documentRequest.content().getBytes(),
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

    @Override
    public String readFile(String filename) {
        ClassPathResource resource = new ClassPathResource(DOCUMENTS+ filename);
        try (InputStream inputStream = resource.getInputStream()) {
                byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);
                return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            logger.error(ERROR_READING_FILE + filename + FILE_EXTENSION_SEPARATOR + e.getMessage());
            return null;
        }
    }
}
