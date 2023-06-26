package com.Credit.credit.Entity;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "credit")
public class Credit {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column
    private int amount;
    @Column
    private int type_id;
    @Column
    private int term_id;
    @Column
    private LocalDate start_date;
    @Column
    private Date end_date;
    @Column
    private int client_id;
    @Column
    //кол-во просрочек
    private int delay_amount;
    @Column
    //основ долг
    private double main_debt;
    @Column
    private boolean credit_status;

    public Credit(int sum, TypeOfCredit typeId, CreditTerm1 creditTerm1) {
        this.amount = sum;
        this.type_id = typeId.getId();
        this.term_id= creditTerm1.getId();
    }

    public Credit() {

    }
    public Credit(int sum, TypeOfCredit type, CreditTerm1 creditTerm1, User user, Date endDate,
                  int delayAmount, double mainDebt, boolean creditStatus) {
        LocalDate now = LocalDate.now();
        this.amount = sum;
        this.type_id = type.getId();
        this.term_id= creditTerm1.getId();
        this.client_id=user.getId();
        this.start_date=now;
        this.end_date=endDate;
        this.delay_amount=delayAmount;
        this.main_debt=mainDebt;
        this.credit_status=creditStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public int getTerm_id() {
        return term_id;
    }

    public void setTerm_id(int term_id) {
        this.term_id = term_id;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getDelay_amount() {
        return delay_amount;
    }

    public void setDelay_amount(int delay_amount) {
        this.delay_amount = delay_amount;
    }

    public double getMain_debt() {
        return main_debt;
    }

    public void setMain_debt(double main_debt) {
        this.main_debt = main_debt;
    }

    public boolean isCredit_status() {
        return credit_status;
    }

    public void setCredit_status(boolean credit_status) {
        this.credit_status = credit_status;
    }

    public CreditTerm1 getCreditTerm1() {
        return creditTerm1;
    }

    public void setCreditTerm1(CreditTerm1 creditTerm1) {
        this.creditTerm1 = creditTerm1;
    }


    public TypeOfCredit getType() {
        return type;
    }

    public void setType(TypeOfCredit type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
    @JoinColumn(name="term_id", insertable = false, updatable = false)
    private CreditTerm1 creditTerm1;


    @ManyToOne(optional=false)
    @JoinColumn(name="type_id", insertable = false, updatable = false)
    private TypeOfCredit type;

    @ManyToOne(optional=false)
    @JoinColumn(name="client_id", insertable = false, updatable = false)
    private User user;

    /*@ManyToOne(optional=false)
    @JoinColumn(name="schedule_id", insertable = false, updatable = false)
    private User user;*/




}
