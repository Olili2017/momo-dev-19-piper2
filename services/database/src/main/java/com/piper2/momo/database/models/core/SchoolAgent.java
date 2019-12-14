package com.piper2.momo.database.models.core;

import com.piper2.momo.database.models.AuditModel;

import javax.persistence.*;

@Entity
@Table(name = "tbl_agents")
public class SchoolAgent extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String telephone;

    public SchoolAgent() {
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
