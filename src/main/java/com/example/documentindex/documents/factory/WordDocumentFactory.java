package com.example.documentindex.documents.factory;

import com.example.documentindex.dto.request.DocumentRequest;
import com.example.documentindex.dto.response.DocumentResponse;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.example.documentindex.util.Constants.*;
import static com.example.documentindex.util.Constants.FILE_CREATED;
import static com.example.documentindex.util.ErrorMessage.CONTENT_ADDITION_ERROR;
import static com.example.documentindex.util.ErrorMessage.FILE_ERROR_CREATION;

public class WordDocumentFactory implements DocumentFactory {

    Logger logger = LoggerFactory.getLogger(WordDocumentFactory.class);

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

            XWPFDocument doc;
            File file = path.toFile();
            if (Files.exists(path)) {
                doc = new XWPFDocument(new FileInputStream(file));
                fileMessage = FILE_EXIST;
            } else {
                doc = new XWPFDocument();
                fileMessage = fileName + FILE_CREATED;
            }
            XWPFParagraph paragraph = doc.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText(documentRequest.content());
            FileOutputStream out = new FileOutputStream(file);
            doc.write(out);
            out.close();
            doc.close();

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