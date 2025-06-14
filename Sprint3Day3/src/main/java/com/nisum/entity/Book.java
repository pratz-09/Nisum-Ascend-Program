package com.nisum.entity;

import com.nisum.validation.ValidYear;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Book {
    private Long id;
    
    @NotBlank(message = "Title is required")
    @Size(min = 1, max = 200, message = "Title must be between 1 and 200 characters")
    private String title;
    
    @NotBlank(message = "Author is required")
    @Size(min = 2, max = 100, message = "Author name must be between 2 and 100 characters")
    private String author;
    
    @NotNull(message = "Published year is required")
    @ValidYear
    private Integer publishedYear;

    public Book() {}

    public Book(Long id, String title, String author, Integer publishedYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public Integer getPublishedYear() { return publishedYear; }
    public void setPublishedYear(Integer publishedYear) { this.publishedYear = publishedYear; }
}