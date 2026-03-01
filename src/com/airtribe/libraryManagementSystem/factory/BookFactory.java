package com.airtribe.libraryManagementSystem.factory;

import com.airtribe.libraryManagementSystem.entity.*;

public class BookFactory {
    public static Book createBook(BookType type, String title, String author, String isbn, int year){
        switch(type){
            case LENDING:
                return new LendingBook(title, author, isbn, year);
            case REFERENCE:
                return new ReferenceBook(title,author,isbn,year);
            case EBOOK:
                return new EBook(title,author,isbn,year,"default-url");
            default:
                throw new IllegalArgumentException("Invalid Book Type");
        }
    }
}
