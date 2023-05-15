package com.example.documentindex.dto.request;


import javax.validation.constraints.NotBlank;

public record SearchRequest(
        @NotBlank(message = "File name must be specified")
        String query,
        @NotBlank(message = "File name must be specified")
        String fileName) {
}
