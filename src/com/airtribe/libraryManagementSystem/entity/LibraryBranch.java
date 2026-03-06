package com.airtribe.libraryManagementSystem.entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class LibraryBranch {

    private final String branchId;
    private final String name;

    // Inventory specific to branch
    private final Map<String, Book> books = new HashMap<>();

    public LibraryBranch(String branchId, String name) {
        this.branchId = branchId;
        this.name = name;
    }

    public String getBranchId() {
        return branchId;
    }

    public String getName() {
        return name;
    }

    public void addBook(Book book) {
        books.put(book.getIsbn(), book);
    }

    public void removeBook(String isbn) {
        books.remove(isbn);
    }

    public Book findBook(String isbn) {
        return books.get(isbn);
    }

    public Map<String, Book> getBooks() {
        return books;
    }

}