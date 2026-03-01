package com.airtribe.libraryManagementSystem.repository;

import com.airtribe.libraryManagementSystem.entity.Book;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BookRepository {
    private Map<String,Book> books = new HashMap<>();
    public void add(Book book){
        books.put(book.getIsbn(),book);
    }
    public Book find(String isbn){
        return books.get(isbn);
    }
    public Collection<Book> getAll() {
        return books.values();
    }
    public void remove(String isbn){
        books.remove(isbn);
    }
}
