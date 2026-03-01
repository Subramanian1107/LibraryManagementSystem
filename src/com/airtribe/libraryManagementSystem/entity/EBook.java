package com.airtribe.libraryManagementSystem.entity;

public class EBook extends Book{
    private String downloadUrl;

    public EBook(String t,String a,
                 String i,int y,
                 String url) {

        super(t,a,i,y);
        this.downloadUrl = url;
    }

    @Override
    public boolean canBorrow() {
        return true;
    }
}
