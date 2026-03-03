package com.airtribe.libraryManagementSystem.service;

import com.airtribe.libraryManagementSystem.entity.Book;
import com.airtribe.libraryManagementSystem.entity.BookStatus;
import com.airtribe.libraryManagementSystem.entity.Patron;
import com.airtribe.libraryManagementSystem.observer.Observer;
import com.airtribe.libraryManagementSystem.repository.BookRepository;

import java.util.logging.Logger;

public class LendingService {
    private BookRepository repository;
    private static final Logger logger =
            Logger.getLogger("Library");

    public LendingService(BookRepository repo) {
        this.repository = repo;
    }

    public void checkout(String isbn, Patron patron) {

        Book book = repository.find(isbn);

        if (book.getStatus()
                != BookStatus.AVAILABLE)
            throw new RuntimeException(
                    "Book unavailable");

        book.borrow();
        patron.addHistory(isbn);

        logger.info("Book borrowed: " + isbn);
    }

    public void returnBook(String isbn) {

        Book book = repository.find(isbn);
        book.returnBook();

        Patron nextPatron = book.nextReservation();

        if (nextPatron != null) {

            for (Observer channel :
                    nextPatron.getNotificationChannels()) {

                channel.update("Your reserved book is available!");
            }

            // Optionally mark book as RESERVED again
            book.reserve(nextPatron);
        }

        logger.info("Book returned");
    }

}
