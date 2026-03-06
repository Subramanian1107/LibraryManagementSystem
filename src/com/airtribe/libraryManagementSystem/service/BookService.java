package com.airtribe.libraryManagementSystem.service;

import com.airtribe.libraryManagementSystem.entity.Book;
import com.airtribe.libraryManagementSystem.entity.BookStatus;
import com.airtribe.libraryManagementSystem.entity.LibraryBranch;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class BookService {

    private static final Logger logger =
            Logger.getLogger("Library");

    private BranchService branchService;

    public BookService(BranchService branchService) {
        this.branchService = branchService;
    }

    public void addBook(Book book, String branchId) {

        LibraryBranch branch = branchService.getBranch(branchId);

        if (branch == null)
            throw new RuntimeException("Branch not found");

        branch.addBook(book);

        logger.info("Book added to branch " + branchId + ": " + book.getIsbn());
    }

    public void removeBook(String isbn, String branchId) {

        LibraryBranch branch = branchService.getBranch(branchId);

        Book book = branch.findBook(isbn);

        if (book == null)
            throw new RuntimeException("Book not found in this branch");

        branch.removeBook(isbn);

        logger.info("Book removed from branch " + branchId + ": " + isbn);
    }

    public Book searchByISBN(String isbn) {

        for (LibraryBranch branch : branchService.getAllBranches()) {

            Book book = branch.findBook(isbn);

            if (book != null)
                return book;
        }

        throw new RuntimeException("Book not found");
    }

    public List<Book> searchByTitle(String title) {

        return branchService.getAllBranches()
                .stream()
                .flatMap(branch -> branch.getBooks().values().stream())
                .filter(book ->
                        book.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }

    public List<Book> availableBooks() {

        return branchService.getAllBranches()
                .stream()
                .flatMap(branch -> branch.getBooks().values().stream())
                .filter(book ->
                        book.getStatus() == BookStatus.AVAILABLE)
                .collect(Collectors.toList());
    }
}