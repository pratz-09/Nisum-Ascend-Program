package com.nisum.controller;

import com.nisum.entity.Book;
import com.nisum.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {
    
    private final List<Book> books = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public BookController() {
        // Initialize with sample data
        books.add(new Book(idGenerator.getAndIncrement(), "Java: The Complete Reference", "Herbert Schildt", 2020));
        books.add(new Book(idGenerator.getAndIncrement(), "Spring in Action", "Craig Walls", 2021));
        books.add(new Book(idGenerator.getAndIncrement(), "Clean Code", "Robert Martin", 2018));
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) Integer publishedYear,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        List<Book> filteredBooks = books.stream()
                .filter(book -> author == null || book.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .filter(book -> publishedYear == null || book.getPublishedYear().equals(publishedYear))
                .collect(Collectors.toList());
        
        // Simple pagination
        int start = page * size;
        int end = Math.min(start + size, filteredBooks.size());
        
        if (start >= filteredBooks.size()) {
            return ResponseEntity.ok(new ArrayList<>());
        }
        
        List<Book> paginatedBooks = filteredBooks.subList(start, end);
        return ResponseEntity.ok(paginatedBooks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = books.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        return ResponseEntity.ok(book);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        book.setId(idGenerator.getAndIncrement());
        books.add(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody Book bookDetails) {
        Book book = books.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setPublishedYear(bookDetails.getPublishedYear());
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        boolean removed = books.removeIf(b -> b.getId().equals(id));
        if (!removed) {
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }
        return ResponseEntity.noContent().build();
    }
}