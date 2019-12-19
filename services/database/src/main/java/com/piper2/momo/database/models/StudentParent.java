package com.piper2.momo.database.models;

import javax.persistence.*;

@Entity
@Table(name = "tbl_parents")
public class StudentParent extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String telephone;

    public StudentParent() {
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
}
