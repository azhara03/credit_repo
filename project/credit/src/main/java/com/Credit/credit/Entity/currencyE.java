package com.Credit.credit.Entity;

import javax.persistence.*;

@Entity
@Table(name="currency")
public class currencyE {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column
    private String currencyname;


    public currencyE() {
    }
    public currencyE(String currencyname) {
        this.currencyname = currencyname;
    }

    public int getId() {
        return id;
    }

    public String getCurrencyname() {
        return currencyname;
    }

    public void setCurrency(String currencyname) {
        this.currencyname = currencyname;
    }
}
