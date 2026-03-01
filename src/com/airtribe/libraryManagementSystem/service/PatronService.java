package com.airtribe.libraryManagementSystem.service;

import com.airtribe.libraryManagementSystem.entity.Patron;
import com.airtribe.libraryManagementSystem.repository.PatronRepository;

import java.util.logging.Logger;

public class PatronService {
    private PatronRepository repository;

    private static final Logger logger =
            Logger.getLogger("Library");

    public PatronService(
            PatronRepository repository) {

        this.repository = repository;
    }

    public void registerPatron(
            String id,
            String name) {

        Patron patron =
                new Patron(id, name);

        repository.add(patron);

        logger.info(
                "Patron registered: " + name);
    }

    public Patron getPatron(String id) {
        return repository.find(id);
    }
}
