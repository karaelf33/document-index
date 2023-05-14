package com.example.documentindex.exception;

public class MissingFileExtensionException extends RuntimeException {
    private final String fileName;

    public MissingFileExtensionException(String fileName) {
        super("File has no extension: " + fileName);
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}