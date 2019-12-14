package com.piper2.momo.database.models.core;

import com.piper2.momo.database.models.AuditModel;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_transactions")
public class Transaction extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private long fromAccount;
    private long toAccount;
    private float amount;

    public Transaction() {
    }

    public Transaction(long from, long to, float amount) {
        this.fromAccount = from;
        this.toAccount = to;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public long getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(long fromAccount) {
        this.fromAccount = fromAccount;
    }

    public long getToAccount() {
        return toAccount;
    }

    public void setToAccount(long toAccount) {
        this.toAccount = toAccount;
    }
}
