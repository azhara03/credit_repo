package com.Credit.credit.Entity;

import javax.persistence.*;

@Entity
@Table(name = "interestrate")
public class InterestRate {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column
    private int type_id;
    @Column
    private int currency_id;
    @Column
    private double delay_percent;
    @Column
    private int percent;

    public int getId() {
        return id;
    }

    public int getType_id() {
        return type_id;
    }

    public int getCurrency_id() {
        return currency_id;
    }

    public double getDelay_percent() {
        return delay_percent;
    }

    public int getPercent() {
        return percent;
    }

    @ManyToOne(optional=false)
    @JoinColumn(name="type_id", insertable = false, updatable = false)
    private TypeOfCredit typeOfCredit;
    @ManyToOne(optional=false)
    @JoinColumn(name="currency_id", insertable = false, updatable = false)
    private currencyE currencyE;
}
