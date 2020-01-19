package com.piper2.momo.android.digitalbursar.models;

public class Child {

    private String name;
    private String firstName;
    private String image;
    private long accountNumber;

    public Child() {
    }

    public Child(String name){
        this.name = name;
        this.firstName = name.split(" ")[0];
    }

    public Child(String name, long acc) {
        this.name = name;
        this.firstName = name.split(" ")[0];
        this.accountNumber = acc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }
}
