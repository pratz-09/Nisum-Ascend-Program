package com.nisum.service;

import com.nisum.dto.BookDto;
import java.util.List;

public interface BookService {
    BookDto create(BookDto dto);
    BookDto get(Long id);
    BookDto update(Long id, BookDto dto);
    void delete(Long id);
    List<BookDto> list();
}