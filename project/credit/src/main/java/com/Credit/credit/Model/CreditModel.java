package com.Credit.credit.Model;

import com.Credit.credit.Entity.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CreditModel {

    private int sum;
    private TypeOfCredit type;
    private CreditTerm1 creditTerm1;
    private User user;
    private Date start_date;
    private Date end_date;
    //кол-во просрочек
    private int delay_amount;
    //основ долг
    //896
    private double main_debt;
    private boolean credit_status;
    //private int schedule_id;
    public CreditModel(int sum, TypeOfCredit typeId, CreditTerm1 creditTerm1) {
        this.sum = sum;
        this.type = typeId;
        this.creditTerm1 = creditTerm1;
    }

    public CreditModel(int sum, TypeOfCredit type, CreditTerm1 creditTerm1, User user, Date start_date, Date end_date, int delay_amount, double main_debt, boolean credit_status) {
        this.sum = sum;
        this.type = type;
        this.creditTerm1 = creditTerm1;
        this.user = user;
        this.start_date = start_date;
        this.end_date = end_date;
        this.delay_amount = delay_amount;
        this.main_debt = main_debt;
        this.credit_status = credit_status;
    }
}
