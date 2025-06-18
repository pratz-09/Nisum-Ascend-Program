
package com.example.project.library;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/books")
public class BookController {
    private Map<Long, Book> books = new HashMap<>();
    private long idCounter = 1;

    @GetMapping
    public List<Book> getAll() {
        return new ArrayList<>(books.values());
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable Long id) {
        return books.get(id);
    }

    @PostMapping
    public Book create(@RequestBody Book book) {
        book.setId(idCounter++);
        books.put(book.getId(), book);
        return book;
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book updated) {
        updated.setId(id);
        books.put(id, updated);
        return updated;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        books.remove(id);
    }
}
