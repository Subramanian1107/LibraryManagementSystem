package com.airtribe.libraryManagementSystem.service;

import com.airtribe.libraryManagementSystem.entity.Book;
import com.airtribe.libraryManagementSystem.entity.BookStatus;
import com.airtribe.libraryManagementSystem.entity.LibraryBranch;
import com.airtribe.libraryManagementSystem.entity.Patron;
import com.airtribe.libraryManagementSystem.observer.Observer;

import java.util.logging.Logger;

public class LendingService {

    private final BranchService branchService;

    private static final Logger logger =
            Logger.getLogger("Library");

    public LendingService(BranchService branchService) {
        this.branchService = branchService;
    }

    public void checkout(String isbn,
                         Patron patron,
                         String branchId) {

        LibraryBranch branch =
                branchService.getBranch(branchId);

        if (branch == null)
            throw new RuntimeException("Invalid branch");

        Book book = branch.findBook(isbn);

        if (book == null)
            throw new RuntimeException("Book not found in branch");

        if (book.getStatus() != BookStatus.AVAILABLE)
            throw new RuntimeException("Book unavailable");

        book.borrow();
        patron.addHistory(isbn);

        logger.info("Book borrowed: "
                + isbn + " from branch " + branchId);
    }

    public void returnBook(String isbn,
                           String branchId) {

        LibraryBranch branch =
                branchService.getBranch(branchId);

        if (branch == null)
            throw new RuntimeException("Invalid branch");

        Book book = branch.findBook(isbn);

        if (book == null)
            throw new RuntimeException("Book not found in branch");

        book.returnBook();

        Patron nextPatron = book.nextReservation();

        if (nextPatron != null) {

            for (Observer channel :
                    nextPatron.getNotificationChannels()) {

                channel.update(
                        "Your reserved book is available at branch "
                                + branch.getName());
            }
        }

        logger.info("Book returned: "
                + isbn + " at branch " + branchId);
    }
}
