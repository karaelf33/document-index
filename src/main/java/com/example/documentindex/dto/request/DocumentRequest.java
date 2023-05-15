package com.example.documentindex.dto.request;


import javax.validation.constraints.NotBlank;

public record DocumentRequest(
        @NotBlank(message = "File name must be specified")
        String fileName,
        @NotBlank(message = "Content must be provided")
        String content) {
}
