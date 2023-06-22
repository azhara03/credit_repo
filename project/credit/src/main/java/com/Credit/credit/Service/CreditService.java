package com.Credit.credit.Service;

import com.Credit.credit.Entity.Credit;
import com.Credit.credit.Entity.CreditTerm1;
import com.Credit.credit.Model.CreditModel;

import java.util.List;

public interface CreditService {
    double calculate(CreditModel model);
    List<Credit> findAll();
}
