package com.piper2.momo.database.models.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.piper2.momo.database.models.AuditModel;

import javax.persistence.*;

@Entity
@Table(name = "tbl_students")
@JsonIgnoreProperties(
        value = { "id", "createdAt", "updatedAt", "pin"}
)
public class Student extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String parent;
    private int pin;

    private long accountNumber;

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
}
