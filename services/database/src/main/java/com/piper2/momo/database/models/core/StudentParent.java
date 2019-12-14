package com.piper2.momo.database.models.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.piper2.momo.database.models.AuditModel;

import javax.persistence.*;

@Entity
@Table(name = "tbl_parents")
@JsonIgnoreProperties(
        value = { "id", "createdAt", "updatedAt", "pin"}
)
public class StudentParent extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String telephone;
    private long accountNumber;
    private int pin;

    public StudentParent() {
    }

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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
}
