package com.piper2.momo.android.digitalbursar.models;

public class SendMoneyDAO {

    private float amount;
    private String from;

    public SendMoneyDAO(String from, float amount) {
        this.amount = amount;
        this.from = from;
    }

    public float getAmount() {
        return amount;
    }

    public String getFrom() {
        return from;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
