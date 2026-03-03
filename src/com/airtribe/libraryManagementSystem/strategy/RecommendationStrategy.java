package com.airtribe.libraryManagementSystem.strategy;

import com.airtribe.libraryManagementSystem.entity.Book;
import com.airtribe.libraryManagementSystem.entity.Patron;

import java.util.List;

public interface RecommendationStrategy {

    List<Book> recommend(Patron patron,
                         List<Book> allBooks);
}