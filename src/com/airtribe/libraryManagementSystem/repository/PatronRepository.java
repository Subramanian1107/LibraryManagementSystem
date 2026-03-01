package com.airtribe.libraryManagementSystem.repository;

import com.airtribe.libraryManagementSystem.entity.Patron;

import java.util.HashMap;
import java.util.Map;

public class PatronRepository {
    private Map<String, Patron> patrons = new HashMap<>();

    public void add(Patron patron) {
        patrons.put(patron.getId(), patron);
    }

    public Patron find(String id) {
        return patrons.get(id);
    }
}
