package com.nisum.SpringDemo;

import com.nisum.SpringDemo.Max;
import com.nisum.SpringDemo.Min;
import com.nisum.SpringDemo.NotBlank;
import com.nisum.SpringDemo.Size;
import jakarta.validation.constraints.*;
import org.jetbrains.annotations.NotNull;

import java.time.Year;

public class Book {
    private Long id;
    
    @NotBlank(message = "Title is required")
    @Size(min = 1, max = 200, message = "Title must be between 1 and 200 characters")
    private String title;
    
    @NotBlank(message = "Author is required")
    @Size(min = 2, max = 100, message = "Author name must be between 2 and 100 characters")
    private String author;
    
    @Min(value = 1000, message = "Published year must be after year 1000")
    @Max(value = 2030, message = "Published year cannot be in the future beyond 2030")
    private Integer publishedYear;
    
    // Constructors
    public Book() {}
    
    public Book(Long id, String title, String author, Integer publishedYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    
    public Integer getPublishedYear() { return publishedYear; }
    public void setPublishedYear(Integer publishedYear) { this.publishedYear = publishedYear; }
}