package com.piper2.momo.database.models;

import javax.persistence.*;

@Entity
@Table(name = "tbl_accounts")
public class Account extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public Account() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
