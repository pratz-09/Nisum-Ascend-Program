package com.nisum.service;

import com.nisum.dto.BookDto;
import com.nisum.model.Book;
import com.nisum.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository repo;

    private BookDto toDto(Book book) {
        BookDto dto = new BookDto();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setPages(book.getPages());
        return dto;
    }

    private Book toEntity(BookDto dto) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setPages(dto.getPages());
        return book;
    }

    @Override
    public BookDto create(BookDto dto) {
        Book book = repo.save(toEntity(dto));
        return toDto(book);
    }

    @Override
    public BookDto get(Long id) {
        return repo.findById(id).map(this::toDto).orElse(null);
    }

    @Override
    public BookDto update(Long id, BookDto dto) {
        Book book = repo.findById(id).orElseThrow();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setPages(dto.getPages());
        return toDto(repo.save(book));
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<BookDto> list() {
        return repo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }
}