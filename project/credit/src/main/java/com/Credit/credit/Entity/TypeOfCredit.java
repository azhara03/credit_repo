package com.Credit.credit.Entity;

import javax.persistence.*;

@Entity
    @Table(name="credittype")
public class TypeOfCredit {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column
    private String creditname;
    @Column
    private int percent;

    public int getPercent() {
        return percent;
    }

    public TypeOfCredit() {
    }
    public TypeOfCredit(String typeName) {
        this.creditname = typeName;
    }

    public int getId() {
        return id;
    }

    public String getCreditname() {
        return creditname;
    }

    public void setTypeName(String typeName) {
        this.creditname = typeName;
    }
}
