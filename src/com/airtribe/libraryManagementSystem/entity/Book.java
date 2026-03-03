package com.airtribe.libraryManagementSystem.entity;

import com.airtribe.libraryManagementSystem.observer.Observer;

import java.util.LinkedList;
import java.util.Queue;

public abstract class Book {
    private final String title;
    private final String author;
    private final String isbn;
    private final int year;

    private BookStatus status;
    private final Queue<Patron> reservationList;
    public Book(String title, String author,
                String isbn, int year) {

        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.year = year;

        status = BookStatus.AVAILABLE;
        reservationList = new LinkedList<>();
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getYear() {
        return year;
    }

    public BookStatus getStatus() {
        return status;
    }


    public void returnBook() {
        status = BookStatus.AVAILABLE;
    }

    public abstract boolean canBorrow();

    public void borrow() {
        if (!canBorrow())
            throw new RuntimeException(
                    "Book cannot be borrowed");

        status = BookStatus.BORROWED;
    }
    public void reserve(Patron patron) {
        reservationList.add(patron);
        status = BookStatus.RESERVED;
    }

    public Patron nextReservation() {
        return reservationList.poll();
    }
}
