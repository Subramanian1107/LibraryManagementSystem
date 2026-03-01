package com.airtribe.libraryManagementSystem.entity;

public class LendingBook extends Book{
    public LendingBook(String t,String a,
                       String i,int y) {
        super(t,a,i,y);
    }

    @Override
    public boolean canBorrow() {
        return true;
    }
}
