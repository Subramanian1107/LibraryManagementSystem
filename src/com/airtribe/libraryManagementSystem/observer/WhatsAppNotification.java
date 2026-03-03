package com.airtribe.libraryManagementSystem.observer;

public class WhatsAppNotification
        implements Observer {

    private String phoneNumber;

    public WhatsAppNotification(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void update(String message) {
        System.out.println(
                "Sending WhatsApp to "
                        + phoneNumber + ": " + message);
    }
}