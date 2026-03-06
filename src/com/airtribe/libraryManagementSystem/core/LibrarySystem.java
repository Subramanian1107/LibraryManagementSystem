package com.airtribe.libraryManagementSystem.core;


import com.airtribe.libraryManagementSystem.repository.PatronRepository;
import com.airtribe.libraryManagementSystem.service.*;
import com.airtribe.libraryManagementSystem.strategy.HistoryBasedRecommendation;

public class LibrarySystem {
    private static LibrarySystem instance;
    private BranchService branchService;
    private BookService bookService;
    private PatronService patronService;
    private LendingService lendingService;
    private RecommendationService recommendationService;

    private LibrarySystem() {


        PatronRepository patronRepo =
                new PatronRepository();
        branchService = new BranchService();
        bookService =
                new BookService(branchService);

        patronService =
                new PatronService(patronRepo);

        lendingService =
                new LendingService(branchService);
        recommendationService =
                new RecommendationService(
                        new HistoryBasedRecommendation(),
                        branchService);
    }

    public static LibrarySystem getInstance() {
        if(instance == null){
            synchronized(LibrarySystem.class){
                if(instance == null){
                    instance = new LibrarySystem();
                }
            }
        }
        return instance;
    }
    public RecommendationService recommend() {
        return recommendationService;
    }

    public BookService books() {
        return bookService;
    }

    public PatronService patrons() {
        return patronService;
    }

    public LendingService lending() {
        return lendingService;
    }

    public BranchService branches() {
        return branchService;
    }
}
