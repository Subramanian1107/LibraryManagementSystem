package com.airtribe.libraryManagementSystem.service;

import com.airtribe.libraryManagementSystem.entity.Book;
import com.airtribe.libraryManagementSystem.entity.LibraryBranch;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BranchService {

    private final Map<String, LibraryBranch> branches = new HashMap<>();

    public void addBranch(String id, String name) {
        branches.put(id, new LibraryBranch(id, name));
    }

    public LibraryBranch getBranch(String id) {
        return branches.get(id);
    }
    public void transferBook(String isbn,
                             String fromBranchId,
                             String toBranchId) {

        LibraryBranch from = branches.get(fromBranchId);
        LibraryBranch to = branches.get(toBranchId);

        if (from == null || to == null)
            throw new RuntimeException("Invalid branch");

        Book book = from.findBook(isbn);

        if (book == null)
            throw new RuntimeException("Book not found in source branch");

        from.removeBook(isbn);
        to.addBook(book);
    }
    public Collection<LibraryBranch> getAllBranches() {
        return branches.values();
    }

}