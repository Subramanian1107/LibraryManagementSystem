package com.airtribe.libraryManagementSystem.service;

import com.airtribe.libraryManagementSystem.entity.Book;

import com.airtribe.libraryManagementSystem.entity.LibraryBranch;
import com.airtribe.libraryManagementSystem.entity.Patron;
import com.airtribe.libraryManagementSystem.strategy.RecommendationStrategy;

import java.util.ArrayList;
import java.util.List;

public class RecommendationService {

    private RecommendationStrategy strategy;
    private BranchService branchService;

    public RecommendationService(
            RecommendationStrategy strategy,
            BranchService branchService) {

        this.strategy = strategy;
        this.branchService = branchService;
    }

    public void setStrategy(RecommendationStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Book> recommend(Patron patron) {

        List<Book> allBooks = new ArrayList<>();

        for (LibraryBranch branch : branchService.getAllBranches()) {
            allBooks.addAll(branch.getBooks().values());
        }

        return strategy.recommend(patron, allBooks);
    }
}