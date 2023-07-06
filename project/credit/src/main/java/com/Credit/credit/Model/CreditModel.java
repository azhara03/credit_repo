package com.Credit.credit.Model;

import com.Credit.credit.Entity.InterestRate;
import com.Credit.credit.Entity.User;
import com.Credit.credit.Entity.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CreditModel {

    private int sum;
    private int month;
    private InterestRate interestRate;
    private User user;
    private Date start_date;
    private Date end_date;
    //кол-во просрочек
    private int delay_amount;
    //основ долг
    //896
    private double main_debt;
    private boolean repaid_status;
    private double month_pay;
    public CreditModel(int sum, InterestRate interestRate, int term) {
        this.sum = sum;
        this.interestRate = interestRate;
        this.month=term;
    }

    public CreditModel(int sum, InterestRate interestRate, User user, Date start_date, Date end_date, int delay_amount, double main_debt, boolean credit_status, double month_pay) {
        this.sum = sum;
        this.interestRate = interestRate;
        //this.creditTerm1 = creditTerm1;
        this.user = user;
        this.start_date = start_date;
        this.end_date = end_date;
        this.delay_amount = delay_amount;
        this.main_debt = main_debt;
        this.repaid_status = credit_status;
        this.month_pay=month_pay;
    }
}
