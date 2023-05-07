package com.example.documentindex.documents.factory;

import com.example.documentindex.dto.request.DocumentRequest;
import com.example.documentindex.dto.response.DocumentResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.example.documentindex.util.Constants.*;
import static com.example.documentindex.util.ErrorMessage.*;
@Slf4j
@Component
public class WordDocumentFactory implements DocumentFactory {
    
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
            log.info(fileMessage, contentMessage);
        } catch (IOException e) {
            fileMessage = FILE_ERROR_CREATION + fileName;
            contentMessage = CONTENT_ADDITION_ERROR;
            log.error(fileMessage, contentMessage, e.getMessage());
        }
        return new DocumentResponse(fileMessage, contentMessage);
    }

    @Override
    public String readFile(String filename) {
        ClassPathResource resource = new ClassPathResource(DOCUMENTS + filename);
        try (InputStream inputStream = resource.getInputStream()) {

            XWPFDocument document = new XWPFDocument(inputStream);
            XWPFWordExtractor extractor = new XWPFWordExtractor(document);
            log.info("File read: {}", filename);
            return extractor.getText();
        } catch (IOException e) {
            log.error(ERROR_READING_FILE + filename + FILE_EXTENSION_SEPARATOR + e.getMessage());
            return null;
        }
    }


}