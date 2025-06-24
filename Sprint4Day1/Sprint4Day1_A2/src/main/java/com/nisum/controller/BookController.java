package com.nisum.controller;

import com.nisum.dto.BookDto;
import com.nisum.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService service;

    @PostMapping
    public BookDto create(@Valid @RequestBody BookDto dto) {
        return service.create(dto);
    }

    @GetMapping("/{id}")
    public BookDto get(@PathVariable Long id) {
        return service.get(id);
    }

    @PutMapping("/{id}")
    public BookDto update(@PathVariable Long id, @Valid @RequestBody BookDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping
    public List<BookDto> list() {
        return service.list();
    }
}