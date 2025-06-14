package com.nisum.SpringDemo.controller;

import com.nisum.SpringDemo.Book_1;
import com.nisum.SpringDemo.Valid;
import com.nisum.SpringDemo.entity.Book;
import com.nisum.SpringDemo.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {
    
    private final ConcurrentHashMap<Long, Book_1> books = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    
    // Initialize with sample data
    public BookController() {
        books.put(1L, new Book_1(1L, "Spring Boot in Action", "Craig Walls", 2018));
        books.put(2L, new Book_1(2L, "Java: The Complete Reference", "Herbert Schildt", 2020));
        books.put(3L, new Book_1(3L, "Clean Code", "Robert Martin", 2008));
        idGenerator.set(4);
    }
    
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) Integer publishedYear) {
        
        List<Book> filteredBooks = books.values().stream()
                .filter(book -> author == null || book.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .filter(book -> publishedYear == null || book.getPublishedYear().equals(publishedYear))
                .skip((long) page * size)
                .limit(size)
                .collect(Collectors.toList());
                
        return ResponseEntity.ok(filteredBooks);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = books.get(id);
        if (book == null) {
            throw new ProductNotFoundException("Book not found with id: " + id);
        }
        return ResponseEntity.ok(book);
    }
    
    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        Long id = idGenerator.getAndIncrement();
        book.setId(id);
        books.put(id, book);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody Book bookDetails) {
        Book existingBook = books.get(id);
        if (existingBook == null) {
            throw new ProductNotFoundException("Book not found with id: " + id);
        }
        
        bookDetails.setId(id);
        books.put(id, bookDetails);
        return ResponseEntity.ok(bookDetails);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        Book book = books.remove(id);
        if (book == null) {
            throw new ProductNotFoundException("Book not found with id: " + id);
        }
        return ResponseEntity.noContent().build();
    }
}