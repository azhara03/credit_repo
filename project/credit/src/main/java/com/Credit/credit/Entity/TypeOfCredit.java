package com.Credit.credit.Entity;

import javax.persistence.*;

@Entity
    @Table(name="credittype")
public class TypeOfCredit {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column
    private String credit_name;

    public TypeOfCredit() {
    }
    public TypeOfCredit(String typeName) {
        this.credit_name = typeName;
    }

    public int getId() {
        return id;
    }

    public String getCredit_name() {
        return credit_name;
    }

    public void setCredit_name(String credit_name) {
        this.credit_name = credit_name;
    }
}
