package com.airtribe.libraryManagementSystem;

import com.airtribe.libraryManagementSystem.core.LibrarySystem;
import com.airtribe.libraryManagementSystem.entity.Book;
import com.airtribe.libraryManagementSystem.entity.BookType;
import com.airtribe.libraryManagementSystem.entity.Patron;
import com.airtribe.libraryManagementSystem.factory.BookFactory;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        LibrarySystem system = LibrarySystem.getInstance();
        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running) {

            System.out.println("\n===== Library Management System =====");
            System.out.println("1. Add Book");
            System.out.println("2. Register Patron");
            System.out.println("3. Checkout Book");
            System.out.println("4. Return Book");
            System.out.println("5. Get Recommendations");
            System.out.println("6. Exit");
            System.out.print("Select option: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {

                case 1:
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
                    system.books().addBook(book);

                    System.out.println("Book added successfully.");
                    break;

                case 2:
                    System.out.print("Patron ID: ");
                    String patronId = scanner.nextLine();

                    System.out.print("Name: ");
                    String name = scanner.nextLine();

                    system.patrons().registerPatron(patronId, name);
                    System.out.println("Patron registered successfully.");
                    break;

                case 3:
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

                case 4:
                    System.out.print("ISBN: ");
                    String returnIsbn = scanner.nextLine();
                    System.out.print("Branch ID: ");
                    String returnBranchId = scanner.nextLine();
                    system.lending().returnBook(returnIsbn,returnBranchId);

                    System.out.println("Book returned.");
                    break;

                case 5:
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

                case 6:
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