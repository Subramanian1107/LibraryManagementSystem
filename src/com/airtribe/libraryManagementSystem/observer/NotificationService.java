package com.airtribe.libraryManagementSystem.observer;

public class NotificationService implements Observer{

    private String patronName;

    public NotificationService(String patronName) {
        this.patronName = patronName;
    }

    public void update(String message) {
        System.out.println(
                "Notify " + patronName + ": " + message);
    }
}
