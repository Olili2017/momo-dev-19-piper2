package com.piper2.momo.android.digitalbursar.models;

import androidx.annotation.NonNull;

public class Child {

    private String name;
    private String firstName;
    private String image;
    private String parent, pin;
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

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @NonNull
    @Override
    public String toString() {
        return "Name: " + this.name + "\nParent: " + this.parent;
    }
}
