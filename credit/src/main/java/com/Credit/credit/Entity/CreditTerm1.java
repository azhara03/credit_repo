package com.Credit.credit.Entity;

import javax.persistence.*;

@Entity
@Table(name="creditterm")
public class CreditTerm1 {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column
    private int term;
    public CreditTerm1() {
    }
    public CreditTerm1(int term) {
        this.term = term;
    }

    public int getId() {
        return id;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }
}
