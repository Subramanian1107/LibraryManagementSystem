package com.airtribe.libraryManagementSystem.entity;

public class ReferenceBook extends Book{
    public ReferenceBook(String t,String a,
                       String i,int y) {
        super(t,a,i,y);
    }

    @Override
    public boolean canBorrow() {
        return false;
    }
}
