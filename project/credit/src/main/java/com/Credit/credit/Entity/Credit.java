package com.Credit.credit.Entity;

import javax.persistence.*;

@Entity
@Table(name = "interestrate")
public class Credit {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column
    private int amount;
    @Column
    private int typeid;
    @Column
    private int currencyid;
    @Column
    private int term;

    public Credit(int sum, TypeOfCredit typeId, com.Credit.credit.Entity.currencyE currencyE, CreditTerm1 creditTerm1) {
        this.amount = sum;
        this.typeid = typeId.getId();
        this.currencyid = currencyE.getId();
        this.term = creditTerm1.getId();
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public int getTypeid() {
        return typeid;
    }

    public int getCurrencyid() {
        return currencyid;
    }

    public int getTerm() {
        return term;
    }

    public CreditTerm1 getCreditTerm1() {
        return creditTerm1;
    }

    public com.Credit.credit.Entity.currencyE getCurrencyE() {
        return currencyE;
    }

    public TypeOfCredit getType() {
        return type;
    }
    public Credit() {

    }
    /*public Credit(int amount, int typeid, int currencyid, int term) {
        this.amount = amount;
        this.typeid = typeid;
        this.currencyid = currencyid;
        this.term = term;
        /*this.creditTerm1 = creditTerm1;
        this.currencyE = currencyE;*/
        //this.type = type;
    //}


    @ManyToOne(optional=false)
    @JoinColumn(name="term", insertable = false, updatable = false)
    private CreditTerm1 creditTerm1;

    @ManyToOne(optional=false)
    @JoinColumn(name="currencyid", insertable = false, updatable = false)
    private currencyE currencyE;

    @ManyToOne(optional=false)
    @JoinColumn(name="typeid", insertable = false, updatable = false)
    private TypeOfCredit type;




}
