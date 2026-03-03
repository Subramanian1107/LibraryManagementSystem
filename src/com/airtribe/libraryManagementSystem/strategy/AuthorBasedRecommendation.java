package com.airtribe.libraryManagementSystem.strategy;

import com.airtribe.libraryManagementSystem.entity.Book;
import com.airtribe.libraryManagementSystem.entity.Patron;
import com.airtribe.libraryManagementSystem.strategy.RecommendationStrategy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AuthorBasedRecommendation
        implements RecommendationStrategy {

    @Override
    public List<Book> recommend(Patron patron,
                                List<Book> allBooks) {

        Set<String> history =
                new HashSet<>(patron.getHistory());

        // Find authors previously read
        Set<String> favoriteAuthors =
                allBooks.stream()
                        .filter(book ->
                                history.contains(
                                        book.getIsbn()))
                        .map(Book::getAuthor)
                        .collect(Collectors.toSet());

        return allBooks.stream()
                .filter(book ->
                        favoriteAuthors.contains(
                                book.getAuthor()))
                .limit(5)
                .collect(Collectors.toList());
    }
}