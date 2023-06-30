package com.Credit.credit.Service;

import com.Credit.credit.Entity.Credit;
import com.Credit.credit.Entity.CreditTerm1;
import com.Credit.credit.Entity.TypeOfCredit;
import com.Credit.credit.Model.CreditModel;
import com.Credit.credit.Model.Platej;

import java.util.List;

public interface CreditService {
    //double calculate(CreditModel model);
    Platej[] calculate(CreditModel model);
    List<Credit> findAll();
    Credit add(CreditModel model);
    Credit getById(Integer creditId);
}
