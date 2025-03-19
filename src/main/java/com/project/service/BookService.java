package com.project.service;

import com.project.model.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing books in the library system.
 * This class demonstrates abstraction by providing a high-level interface
 * for book-related operations.
 */
public class BookService {
    private final List<Book> books;

    public BookService() {
        this.books = new ArrayList<>();
    }

    /**
     * Adds a new book to the library.
     * @param book The book to add
     * @return true if the book was added successfully, false otherwise
     */
    public boolean addBook(Book book) {
        if (book == null || book.getIsbn() == null || book.getIsbn().trim().isEmpty()) {
            return false;
        }
        return books.add(book);
    }

    /**
     * Removes a book from the library by ISBN.
     * @param isbn The ISBN of the book to remove
     * @return true if the book was removed successfully, false otherwise
     */
    public boolean removeBook(String isbn) {
        return books.removeIf(book -> book.getIsbn().equals(isbn));
    }

    /**
     * Finds a book by ISBN.
     * @param isbn The ISBN to search for
     * @return Optional containing the book if found, empty Optional otherwise
     */
    public Optional<Book> findBookByIsbn(String isbn) {
        return books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst();
    }

    /**
     * Gets all books in the library.
     * @return List of all books
     */
    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    /**
     * Updates the availability status of a book.
     * @param isbn The ISBN of the book to update
     * @param available The new availability status
     * @return true if the book was updated successfully, false otherwise
     */
    public boolean updateBookAvailability(String isbn, boolean available) {
        return findBookByIsbn(isbn)
                .map(book -> {
                    book.setAvailable(available);
                    return true;
                })
                .orElse(false);
    }
} 