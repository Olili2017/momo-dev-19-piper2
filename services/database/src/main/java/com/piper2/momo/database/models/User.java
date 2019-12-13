package com.piper2.momo.database.models;

import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;

public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;

    private String telephone;

    private Date dateCreated;

    public int getId() {
        return id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
