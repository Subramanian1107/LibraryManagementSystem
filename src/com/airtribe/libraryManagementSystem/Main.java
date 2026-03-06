package com.airtribe.libraryManagementSystem;

import com.airtribe.libraryManagementSystem.core.LibrarySystem;
import com.airtribe.libraryManagementSystem.entity.Book;
import com.airtribe.libraryManagementSystem.entity.BookType;
import com.airtribe.libraryManagementSystem.entity.LibraryBranch;
import com.airtribe.libraryManagementSystem.entity.Patron;
import com.airtribe.libraryManagementSystem.factory.BookFactory;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        LibrarySystem system = LibrarySystem.getInstance();
        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running) {

            System.out.println("\n===== Library Management System =====");
            System.out.println("1. Add Branch");
            System.out.println("2. Add Book");
            System.out.println("3. Register Patron");
            System.out.println("4. Checkout Book");
            System.out.println("5. Return Book");
            System.out.println("6. Get Recommendations");
            System.out.println("7. Reserve Book");
            System.out.println("8. View Branch Inventory");
            System.out.println("9. Exit");
            System.out.print("Select option: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter Branch ID: ");
                    String branchId = scanner.nextLine();

                    System.out.print("Enter Branch Name: ");
                    String branchName = scanner.nextLine();

                    LibraryBranch branch = new LibraryBranch(branchId, branchName);

                    system.branches().addBranch(branchId, branchName);

                    System.out.println("Branch added successfully.");
                    break;
                case 2:
                    try {
                        System.out.print("Enter Branch ID: ");
                        String bookBranchId = scanner.nextLine();

                        System.out.print("Enter Book Type (LENDING / REFERENCE): ");
                        BookType type = BookType.valueOf(scanner.nextLine().toUpperCase());

                        System.out.print("Title: ");
                        String title = scanner.nextLine();

                        System.out.print("Author: ");
                        String author = scanner.nextLine();

                        System.out.print("ISBN: ");
                        String isbn = scanner.nextLine();

                        System.out.print("Publication Year: ");
                        int year = Integer.parseInt(scanner.nextLine());

                        Book book = BookFactory.createBook(type, title, author, isbn, year);

                        system.books().addBook(book, bookBranchId);

                        System.out.println("Book added successfully to branch " + bookBranchId);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Patron ID: ");
                    String patronId = scanner.nextLine();

                    System.out.print("Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    System.out.print("Phone number: ");
                    String phoneNumber = scanner.nextLine();

                    system.patrons().registerPatron(patronId, name, email,phoneNumber);
                    System.out.println("Patron registered successfully.");
                    break;

                case 4:
                    try {
                        System.out.print("Branch ID: ");
                        String checkoutBranchId = scanner.nextLine();

                        System.out.print("ISBN: ");
                        String checkoutIsbn = scanner.nextLine();

                        System.out.print("Patron ID: ");
                        String checkoutPatronId = scanner.nextLine();

                        Patron checkoutPatron =
                                system.patrons().getPatron(checkoutPatronId);

                        system.lending().checkout(
                                checkoutIsbn,
                                checkoutPatron,
                                checkoutBranchId
                        );

                        System.out.println("Book checked out successfully.");

                    } catch (RuntimeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 5:
                    System.out.print("ISBN: ");
                    String returnIsbn = scanner.nextLine();
                    System.out.print("Branch ID: ");
                    String returnBranchId = scanner.nextLine();
                    system.lending().returnBook(returnIsbn,returnBranchId);

                    System.out.println("Book returned.");
                    break;

                case 6:
                    System.out.print("Patron ID: ");
                    String recPatronId = scanner.nextLine();

                    Patron recPatron =
                            system.patrons().getPatron(recPatronId);

                    List<Book> suggestions =
                            system.recommend().recommend(recPatron);

                    System.out.println("Recommended Books:");
                    suggestions.forEach(b ->
                            System.out.println(b.getTitle() + " - " + b.getIsbn()));
                    break;
                case 7:
                    try {
                        System.out.print("Branch ID: ");
                        String reserveBranchId = scanner.nextLine();

                        System.out.print("ISBN: ");
                        String isbn = scanner.nextLine();

                        System.out.print("Patron ID: ");
                        String reservePatronId = scanner.nextLine();

                        Patron patron = system.patrons().getPatron(reservePatronId);

                        system.lending().reserveBook(isbn, patron, reserveBranchId);

                        System.out.println("Book reserved successfully.");

                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 8:
                    try {
                        System.out.print("Branch ID: ");
                        String viewBranchId = scanner.nextLine();
                        Map<String, Book> books = system.branches().getBranch(viewBranchId).getBooks();
                        System.out.println("Books in Branch " + viewBranchId + ":");

                        for (Map.Entry<String, Book> entry : books.entrySet()) {
                            Book book = entry.getValue();

                            System.out.println(
                                    "ISBN: " + entry.getKey() +
                                            ", Title: " + book.getTitle() +
                                            ", Author: " + book.getAuthor() +
                                            ", Status: " + book.getStatus()
                            );
                        }

                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 9:
                    running = false;
                    System.out.println("Exiting system...");
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }
}