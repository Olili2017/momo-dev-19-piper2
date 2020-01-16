package com.piper2.momo.database.models.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.piper2.momo.database.constacts.AccountType;
import com.piper2.momo.database.models.AuditModel;

import javax.persistence.*;

@Entity
@Table(name = "tbl_accounts")
@JsonIgnoreProperties(
        value = { "id", "createdAt", "updatedAt"}
)
public class Account extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private long accountNumber;

    private int type;

    private int owner;

    private String parent;

    private float balance;

    Account() {
    }

    public Account(int type, int owner) {
        this.type = type;
        this.owner = owner;
        this.balance = 0;
        this.accountNumber = createAccountNumber();
    }

    public Account(int type, int owner, String parent) {
        this.type = type;
        this.owner = owner;
        this.parent = parent;
        this.balance = 0;
        this.accountNumber = createAccountNumber();
    }

    private long createAccountNumber(){
        int requiredLength = 9;
//        using 8 at the end because am rounding the number off
        long rand = (long) Math.ceil(Math.random() * 999999998);
        int currentLength = getAccountNumberLength(rand);
        String accountNumber = String.valueOf(rand);

        if(currentLength < requiredLength) {
            accountNumber = String.format("%0"+(requiredLength - currentLength)+"d", rand);
        }

        return Long.parseLong(accountNumber);
    }

    int getAccountNumberLength(long number){
        if(number < 100000){
            if ( number < 100) {
                if (number < 10) return 1;
                else return 2;
            } else {
                if (number < 1000 ) return 3;
                else {
                    if (number < 10000) return 4;
                    else return 5;
                }
            }

        } else {
            if (number < 10000000){
                if (number < 1000000) return 6;
                else return 7;
            } else {
                if (number < 100000000) return 8;
                else {
                    if (number < 1000000000) return 9;
                    else return 10;
                }
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    private int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        if(this.getType() != AccountType.STUDENT){
            this.parent = null;
        } else {
            this.parent = parent;
        }

    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }
}
