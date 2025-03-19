package com.project.test;

import com.project.model.Book;
import com.project.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {
    private BookService bookService;
    private Book testBook;

    @BeforeEach
    void setUp() {
        bookService = new BookService();
        testBook = new Book("1234567890", "Test Book", "Test Author", 2024);
    }

    @Test
    void addBook_ValidBook_ShouldAddSuccessfully() {
        assertTrue(bookService.addBook(testBook));
        assertEquals(1, bookService.getAllBooks().size());
    }

    @Test
    void addBook_NullBook_ShouldNotAdd() {
        assertFalse(bookService.addBook(null));
        assertEquals(0, bookService.getAllBooks().size());
    }

    @Test
    void removeBook_ExistingBook_ShouldRemoveSuccessfully() {
        bookService.addBook(testBook);
        assertTrue(bookService.removeBook("1234567890"));
        assertEquals(0, bookService.getAllBooks().size());
    }

    @Test
    void findBookByIsbn_ExistingBook_ShouldReturnBook() {
        bookService.addBook(testBook);
        assertTrue(bookService.findBookByIsbn("1234567890").isPresent());
        assertEquals(testBook, bookService.findBookByIsbn("1234567890").get());
    }

    @Test
    void findBookByIsbn_NonExistingBook_ShouldReturnEmpty() {
        assertFalse(bookService.findBookByIsbn("1234567890").isPresent());
    }

    @Test
    void updateBookAvailability_ExistingBook_ShouldUpdateSuccessfully() {
        bookService.addBook(testBook);
        assertTrue(bookService.updateBookAvailability("1234567890", false));
        assertFalse(bookService.findBookByIsbn("1234567890").get().isAvailable());
    }

    @Test
    void updateBookAvailability_NonExistingBook_ShouldNotUpdate() {
        assertFalse(bookService.updateBookAvailability("1234567890", false));
    }
} 