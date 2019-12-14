package com.piper2.momo.database.models.auxilially;

import javax.validation.constraints.Null;

public class Withdraw {
    private long from;
    private float amount;

    @Null
    private float balance;

    public Withdraw(long from, float amount, @Null float balance) {
        this.from = from;
        this.amount = amount;
        this.balance = balance;
    }

    public long getFrom() {
        return from;
    }

    public void setFrom(long from) {
        this.from = from;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
