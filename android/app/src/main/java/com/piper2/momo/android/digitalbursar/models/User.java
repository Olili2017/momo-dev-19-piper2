package com.piper2.momo.android.digitalbursar.models;

public class User {
    private String name;
    private int id;
    private String pass;
    private long acc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public long getAcc() {
        return acc;
    }

    public void setAcc(long acc) {
        this.acc = acc;
    }
}
