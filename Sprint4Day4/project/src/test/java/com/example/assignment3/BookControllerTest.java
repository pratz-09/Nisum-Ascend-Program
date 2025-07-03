package com.example.assignment3;

import com.example.assignment3.controller.BookController;
import com.example.assignment3.model.Book;
import com.example.assignment3.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = BookController.class)
@AutoConfigureRestDocs
@DisplayName("Assignment 3: BookController MockMvc Tests")
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Should return 200 and JSON body for valid book ID")
    void shouldReturn200AndJsonBodyForValidId() throws Exception {
        // Given
        Long bookId = 1L;
        Book book = new Book("The Great Gatsby", "F. Scott Fitzgerald", 
                           "978-0-7432-7356-5", new BigDecimal("12.99"), "Fiction");
        book.setId(bookId);

        when(bookService.findById(bookId)).thenReturn(book);

        // When & Then
        mockMvc.perform(get("/api/books/{id}", bookId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(bookId))
                .andExpect(jsonPath("$.title").value("The Great Gatsby"))
                .andExpect(jsonPath("$.author").value("F. Scott Fitzgerald"))
                .andExpect(jsonPath("$.isbn").value("978-0-7432-7356-5"))
                .andExpect(jsonPath("$.price").value(12.99))
                .andExpect(jsonPath("$.genre").value("Fiction"))
                .andDo(document("get-book-by-id",
                        pathParameters(
                                parameterWithName("id").description("The ID of the book to retrieve")
                        ),
                        responseFields(
                                fieldWithPath("id").description("The book's unique identifier"),
                                fieldWithPath("title").description("The book's title"),
                                fieldWithPath("author").description("The book's author"),
                                fieldWithPath("isbn").description("The book's ISBN"),
                                fieldWithPath("price").description("The book's price"),
                                fieldWithPath("genre").description("The book's genre")
                        )
                ));
    }

    @Test
    @DisplayName("Should return 404 for missing book ID")
    void shouldReturn404ForMissingId() throws Exception {
        // Given
        Long bookId = 999L;
        when(bookService.findById(bookId))
                .thenThrow(new BookService.BookNotFoundException("Book not found with id: " + bookId));

        // When & Then
        mockMvc.perform(get("/api/books/{id}", bookId))
                .andExpect(status().isNotFound())
                .andDo(document("get-book-not-found",
                        pathParameters(
                                parameterWithName("id").description("The ID of the book to retrieve")
                        )
                ));
    }

    @Test
    @DisplayName("Should return all books with 200 status")
    void shouldReturnAllBooksWithStatus200() throws Exception {
        // Given
        List<Book> books = Arrays.asList(
            new Book("Book 1", "Author 1", "ISBN-1", new BigDecimal("19.99"), "Fiction"),
            new Book("Book 2", "Author 2", "ISBN-2", new BigDecimal("24.99"), "Science Fiction")
        );
        books.get(0).setId(1L);
        books.get(1).setId(2L);

        when(bookService.findAll()).thenReturn(books);

        // When & Then
        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Book 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("Book 2"))
                .andDo(document("get-all-books",
                        responseFields(
                                fieldWithPath("[].id").description("The book's unique identifier"),
                                fieldWithPath("[].title").description("The book's title"),
                                fieldWithPath("[].author").description("The book's author"),
                                fieldWithPath("[].isbn").description("The book's ISBN"),
                                fieldWithPath("[].price").description("The book's price"),
                                fieldWithPath("[].genre").description("The book's genre")
                        )
                ));
    }

    @Test
    @DisplayName("Should create book and return 201 status")
    void shouldCreateBookAndReturn201() throws Exception {
        // Given
        Book newBook = new Book("New Book", "New Author", "NEW-ISBN", 
                              new BigDecimal("29.99"), "Mystery");
        Book savedBook = new Book("New Book", "New Author", "NEW-ISBN", 
                                new BigDecimal("29.99"), "Mystery");
        savedBook.setId(1L);

        when(bookService.save(any(Book.class))).thenReturn(savedBook);

        // When & Then
        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newBook)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("New Book"))
                .andExpect(jsonPath("$.author").value("New Author"))
                .andDo(document("create-book",
                        requestFields(
                                fieldWithPath("title").description("The book's title"),
                                fieldWithPath("author").description("The book's author"),
                                fieldWithPath("isbn").description("The book's ISBN"),
                                fieldWithPath("price").description("The book's price"),
                                fieldWithPath("genre").description("The book's genre")
                        ),
                        responseFields(
                                fieldWithPath("id").description("The book's unique identifier"),
                                fieldWithPath("title").description("The book's title"),
                                fieldWithPath("author").description("The book's author"),
                                fieldWithPath("isbn").description("The book's ISBN"),
                                fieldWithPath("price").description("The book's price"),
                                fieldWithPath("genre").description("The book's genre")
                        )
                ));
    }

    @Test
    @DisplayName("Should return books by author")
    void shouldReturnBooksByAuthor() throws Exception {
        // Given
        String author = "J.K. Rowling";
        List<Book> books = Arrays.asList(
            new Book("Harry Potter 1", author, "ISBN-HP1", new BigDecimal("19.99"), "Fantasy"),
            new Book("Harry Potter 2", author, "ISBN-HP2", new BigDecimal("21.99"), "Fantasy")
        );
        books.get(0).setId(1L);
        books.get(1).setId(2L);

        when(bookService.findByAuthor(author)).thenReturn(books);

        // When & Then
        mockMvc.perform(get("/api/books/author/{author}", author))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpected(jsonPath("$[0].author").value(author))
                .andExpect(jsonPath("$[1].author").value(author))
                .andDo(document("get-books-by-author",
                        pathParameters(
                                parameterWithName("author").description("The author name to search for")
                        ),
                        responseFields(
                                fieldWithPath("[].id").description("The book's unique identifier"),
                                fieldWithPath("[].title").description("The book's title"),
                                fieldWithPath("[].author").description("The book's author"),
                                fieldWithPath("[].isbn").description("The book's ISBN"),
                                fieldWithPath("[].price").description("The book's price"),
                                fieldWithPath("[].genre").description("The book's genre")
                        )
                ));
    }
}