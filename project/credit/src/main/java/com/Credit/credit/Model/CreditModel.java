package com.Credit.credit.Model;

import com.Credit.credit.Entity.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class CreditModel {


    private int sum;
    private TypeOfCredit type;
    private currencyE currencyE;
    private CreditTerm1 creditTerm1;

    public CreditModel(int sum, TypeOfCredit typeId, com.Credit.credit.Entity.currencyE currencyE, CreditTerm1 creditTerm1) {
        this.sum = sum;
        this.type = typeId;
        this.currencyE = currencyE;
        this.creditTerm1 = creditTerm1;
    }
}
