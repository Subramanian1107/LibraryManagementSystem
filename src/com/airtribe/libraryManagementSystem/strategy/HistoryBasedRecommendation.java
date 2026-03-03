package com.airtribe.libraryManagementSystem.strategy;

import com.airtribe.libraryManagementSystem.entity.Book;
import com.airtribe.libraryManagementSystem.entity.Patron;
import com.airtribe.libraryManagementSystem.strategy.RecommendationStrategy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class HistoryBasedRecommendation
        implements RecommendationStrategy {

    @Override
    public List<Book> recommend(Patron patron,
                                List<Book> allBooks) {

        Set<String> history =
                new HashSet<>(patron.getHistory());

        return allBooks.stream()
                .filter(book ->
                        !history.contains(
                                book.getIsbn()))
                .limit(5)
                .collect(Collectors.toList());
    }
}