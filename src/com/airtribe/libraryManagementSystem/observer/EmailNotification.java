package com.airtribe.libraryManagementSystem.observer;

public class EmailNotification implements Observer {

    private String email;

    public EmailNotification(String email) {
        this.email = email;
    }

    @Override
    public void update(String message) {
        System.out.println(
                "Sending Email to "
                        + email + ": " + message);
    }
}