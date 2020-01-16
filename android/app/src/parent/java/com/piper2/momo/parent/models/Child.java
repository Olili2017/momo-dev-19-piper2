package com.piper2.momo.parent.models;

public class Child {

    private String name;
    private String firstName;
    private String image;
    private long account;

    public Child() {
    }

    public Child(String name){
        this.name = name;
        this.firstName = name.split(" ")[0];
    }

    public Child(String name, long acc) {
        this.name = name;
        this.firstName = name.split(" ")[0];
        this.account = acc;
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

    public long getAccount() {
        return account;
    }

    public void setAccount(long account) {
        this.account = account;
    }
}
