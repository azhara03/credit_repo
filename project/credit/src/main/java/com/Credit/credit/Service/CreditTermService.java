package com.Credit.credit.Service;

import com.Credit.credit.Entity.CreditTerm1;
import com.Credit.credit.Entity.currencyE;

import java.util.List;

public interface CreditTermService {
    List<CreditTerm1> findAll();

    CreditTerm1 getById(Integer id);
}
