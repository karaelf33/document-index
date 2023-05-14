package com.example.documentindex.exception;

import static com.example.documentindex.util.ErrorMessage.UNSUPPORTED_FILE_EXTENSION;

public class UnsupportedFileExtensionException extends RuntimeException {
    private final String fileExtension;

    public UnsupportedFileExtensionException(String fileExtension) {
        super(UNSUPPORTED_FILE_EXTENSION + fileExtension);
        this.fileExtension = fileExtension;
    }

    public String getFileExtension() {
        return fileExtension;
    }
}