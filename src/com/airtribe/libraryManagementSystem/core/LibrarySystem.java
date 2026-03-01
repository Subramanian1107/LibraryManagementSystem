package com.airtribe.libraryManagementSystem.core;

import com.airtribe.libraryManagementSystem.repository.BookRepository;
import com.airtribe.libraryManagementSystem.repository.PatronRepository;
import com.airtribe.libraryManagementSystem.service.BookService;
import com.airtribe.libraryManagementSystem.service.LendingService;
import com.airtribe.libraryManagementSystem.service.PatronService;

public class LibrarySystem {
    private static LibrarySystem instance;

    private BookService bookService;
    private PatronService patronService;
    private LendingService lendingService;

    private LibrarySystem() {

        BookRepository bookRepo =
                new BookRepository();

        PatronRepository patronRepo =
                new PatronRepository();

        bookService =
                new BookService(bookRepo);

        patronService =
                new PatronService(patronRepo);

        lendingService =
                new LendingService(bookRepo);
    }

    public static LibrarySystem getInstance() {
        if(instance == null){
            synchronized(LibrarySystem.class){
                if(instance == null){
                    instance = new LibrarySystem();
                }
            }
        }
        return instance;
    }

    public BookService books() {
        return bookService;
    }

    public PatronService patrons() {
        return patronService;
    }

    public LendingService lending() {
        return lendingService;
    }
}
