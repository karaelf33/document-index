package com.example.documentindex.documents.factory;

import com.example.documentindex.dto.request.DocumentRequest;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.example.documentindex.Constants.RESOURCES_PATH;

public class WordDocumentFactory implements DocumentFactory {

    Logger logger = LoggerFactory.getLogger(WordDocumentFactory.class);
    @Override
    public void saveDocumentWithContent(DocumentRequest documentRequest) {
        try {
            // create the directory if it doesn't exist
            Files.createDirectories(Paths.get(RESOURCES_PATH));

            // create or append content to the file
            Path filePath = Paths.get(RESOURCES_PATH + "/" + documentRequest.getFileName());
            XWPFDocument doc;
            if (Files.exists(filePath)) {
                doc = new XWPFDocument(new FileInputStream(filePath.toFile()));
            } else {
                doc = new XWPFDocument();
            }
            XWPFParagraph paragraph = doc.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText(documentRequest.getContent());
            FileOutputStream out = new FileOutputStream(filePath.toFile());
            doc.write(out);
            out.close();
            doc.close();

            logger.info("Content added successfully for {}", documentRequest.getFileName());
        } catch (IOException e) {
            logger.error("Error creating or writing to file: {}", e.getMessage());
        }
    }
}