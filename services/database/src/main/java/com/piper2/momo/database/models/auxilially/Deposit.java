package com.piper2.momo.database.models.auxilially;

import javax.validation.constraints.Null;

public class Deposit {
    private float amount;
    private long from;
    private long to;

    @Null
    private float balance; // only used in deposit response

    public Deposit() {
    }

    public Deposit(float amount, long to, float balance) {
        this.amount = amount;
        this.to = to;
        this.balance = balance;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public long getFrom() {
        return from;
    }

    public void setFrom(long from) {
        this.from = from;
    }

    public long getTo() {
        return to;
    }

    public void setTo(long to) {
        this.to = to;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
