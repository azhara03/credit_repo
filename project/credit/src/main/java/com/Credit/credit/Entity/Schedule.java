package com.Credit.credit.Entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
/*@NamedStoredProcedureQuery (name = "getAllSchedules",
        procedureName = "sp_schedule",
        resultClasses = Schedule.class)*/
/*@NamedStoredProcedureQuery(
        name = "getAllSchedules",
        procedureName = "sp_schedules7",
        resultClasses = { Schedule.class },
        parameters = {
                @StoredProcedureParameter(
                        mode=ParameterMode.IN,
                        name="id_cr",
                        type = Integer.class
                )
        }
)*/
@Table(name="schedules")
public class Schedule {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column
    private LocalDate payment_date;
    @Column
    private double month_pay;
    @Column
    private int credit_id;
    @Column
    private boolean payment_status ;
    @Column
    private double main_debt;
    @Column
    private double monthly_percent;
    @Column
    private double total_debt;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(LocalDate payment_date) {
        this.payment_date = payment_date;
    }

    public double getMonth_pay() {
        return month_pay;
    }

    public void setMonth_pay(double month_pay) {
        this.month_pay = month_pay;
    }

    public int getCredit_id() {
        return credit_id;
    }

    public void setCredit_id(int credit_id) {
        this.credit_id = credit_id;
    }

    public boolean isPayment_status() {
        return payment_status;
    }

    public void setPayment_status(boolean payment_status) {
        this.payment_status = payment_status;
    }

    public double getMain_debt() {
        return main_debt;
    }

    public void setMain_debt(double main_debt) {
        this.main_debt = main_debt;
    }

    public double getMonthly_percent() {
        return monthly_percent;
    }

    public void setMonthly_percent(double monthly_percent) {
        this.monthly_percent = monthly_percent;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public double getTotal_debt() {
        return total_debt;
    }

    public void setTotal_debt(double total_debt) {
        this.total_debt = total_debt;
    }

    @ManyToOne(optional=false)
    @JoinColumn(name="credit_id", insertable = false, updatable = false)
    private Credit credit;

}
