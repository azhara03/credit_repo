package com.Credit.credit.Service;

import com.Credit.credit.Entity.TypeOfCredit;
import com.Credit.credit.Entity.currencyE;

import java.util.List;

public interface TypeOfCreditService {
    List<TypeOfCredit> findAll();
    TypeOfCredit getById(Integer typeId);
}
