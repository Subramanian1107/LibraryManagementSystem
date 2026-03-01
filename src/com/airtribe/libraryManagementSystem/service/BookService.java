package com.airtribe.libraryManagementSystem.service;

import com.airtribe.libraryManagementSystem.entity.Book;
import com.airtribe.libraryManagementSystem.entity.BookStatus;
import com.airtribe.libraryManagementSystem.repository.BookRepository;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class BookService {
    private BookRepository repository;
    private static final Logger logger =
            Logger.getLogger("Library");

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public void addBook(Book book) {
        repository.add(book);
        logger.info("Book added: "
                + book.getIsbn());
    }

    public void removeBook(String isbn) {
        repository.remove(isbn);
        logger.info("Book removed: " + isbn);
    }

    public Book searchByISBN(String isbn) {
        return repository.find(isbn);
    }

    public List<Book> searchByTitle(String title) {

        return repository.getAll()
                .stream()
                .filter(b ->
                        b.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }

    public List<Book> availableBooks() {

        return repository.getAll()
                .stream()
                .filter(b ->
                        b.getStatus()
                                == BookStatus.AVAILABLE)
                .collect(Collectors.toList());
    }
}
