package com.airtribe.libraryManagementSystem;

import com.airtribe.libraryManagementSystem.core.LibrarySystem;
import com.airtribe.libraryManagementSystem.entity.Book;
import com.airtribe.libraryManagementSystem.entity.BookType;
import com.airtribe.libraryManagementSystem.entity.Patron;
import com.airtribe.libraryManagementSystem.factory.BookFactory;

public class Main {
    public static void main(String[] args) {
        LibrarySystem system =
                LibrarySystem.getInstance();

        Book lendingBook =
                BookFactory.createBook(
                        BookType.LENDING,
                        "Clean Code",
                        "Robert Martin",
                        "ISBN1",
                        2008);
        system.books().addBook(lendingBook);
        Book encyclopedia =
                BookFactory.createBook(
                        BookType.REFERENCE,
                        "Encyclopedia",
                        "Britannica",
                        "ISBN2",
                        2000);
        system.books().addBook(encyclopedia);
        Patron patron =
                new Patron("P1", "Subramanian");

        system.patrons().registerPatron(patron.getId(),patron.getName());

        system.lending()
                .checkout("123", patron);
    }
}
