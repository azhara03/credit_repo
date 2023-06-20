package com.Credit.credit.Entity;

import javax.persistence.*;

@Entity
@Table(name="creditterm")
public class CreditTerm1 {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column
    private String term;
    public CreditTerm1() {
    }
    public CreditTerm1(String term) {
        this.term = term;
    }

    public int getId() {
        return id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
