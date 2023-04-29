package com.example.documentindex.documents.factory;

import com.example.documentindex.dto.request.*;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;

public class WordDocumentFactory implements DocumentFactory {
    @Override
    public void createDocument(DocumentRequest documentRequest) {
        System.out.println("sdfsdf");
    }

    @Override
    public void writeContent(DocumentRequest documentRequest) {
        try {

            XWPFDocument doc = new XWPFDocument();
            XWPFParagraph paragraph = doc.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText(documentRequest.getContent());
            FileOutputStream out = new FileOutputStream(documentRequest.getFileName());
            doc.write(out);
            out.close();
            doc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}