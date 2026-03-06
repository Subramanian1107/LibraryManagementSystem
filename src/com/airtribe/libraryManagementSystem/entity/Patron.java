package com.airtribe.libraryManagementSystem.entity;

import com.airtribe.libraryManagementSystem.observer.Observer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Patron {
    private String id;
    private String name;
    private List<String> history;
    // Notification channels (Email, WhatsApp, SMS etc.)
    private final List<Observer> notificationChannels;
    public Patron(String id, String name) {
        this.id = id;
        this.name = name;
        history = new ArrayList<>();
        this.notificationChannels = new ArrayList<>();
    }
    public void addNotificationChannel(Observer observer) {
        notificationChannels.add(observer);
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
    public List<Observer> getNotificationChannels() {
        return Collections.unmodifiableList(notificationChannels);
    }
}
