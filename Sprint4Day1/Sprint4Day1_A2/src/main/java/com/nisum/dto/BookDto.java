package com.nisum.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class BookDto {
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @Positive
    private int pages;

    public void setTitle(String test) {
    }

    public void setAuthor(String author) {
    }

    public void setPages(int i) {
    }

    public void setId(Long id) {
    }

    public Long getId() {
        return 0L;
    }

    public String getTitle() {
        return "";
    }

    public String getAuthor() {
        return "";
    }

    public int getPages() {
        return 0;
    }

    // getters and setters
}