package com.airtribe.libraryManagementSystem.entity;

import java.util.ArrayList;
import java.util.List;

public class Patron {
    private String id;
    private String name;
    private List<String> history;
    public Patron(String id, String name) {
        this.id = id;
        this.name = name;
        history = new ArrayList<>();
    }

    public void addHistory(String isbn) {
        history.add(isbn);
    }

    public String getName() {
        return name;
    }

    public List<String> getHistory() {
        return history;
    }

    public String getId() {
        return id;
    }
}
