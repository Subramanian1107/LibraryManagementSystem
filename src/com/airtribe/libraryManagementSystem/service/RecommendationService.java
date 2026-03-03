package com.airtribe.libraryManagementSystem.service;

import com.airtribe.libraryManagementSystem.entity.Book;
import com.airtribe.libraryManagementSystem.entity.Patron;
import com.airtribe.libraryManagementSystem.repository.BookRepository;
import com.airtribe.libraryManagementSystem.strategy.RecommendationStrategy;

import java.util.ArrayList;
import java.util.List;

public class RecommendationService {

    private RecommendationStrategy strategy;
    private BookRepository repository;

    public RecommendationService(
            RecommendationStrategy strategy,
            BookRepository repository) {

        this.strategy = strategy;
        this.repository = repository;
    }

    public void setStrategy(
            RecommendationStrategy strategy) {

        this.strategy = strategy;
    }

    public List<Book> recommend(
            Patron patron) {

        return strategy.recommend(
                patron,
                new ArrayList<>(
                        repository.getAll()));
    }
}