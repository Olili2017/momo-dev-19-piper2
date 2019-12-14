package com.piper2.momo.database.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_transactions")
public class Transaction extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int user;
    private float amount;
    private Date date;

    public Transaction() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
