package com.Credit.credit.Service;

import com.Credit.credit.Model.CreditModel;
import com.Credit.credit.Entity.Credit;
import com.Credit.credit.Model.Platej;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface CreditService {
    //double calculate(CreditModel model);
    Platej[] calculate(CreditModel model);
    List<Credit> findAll();
    Credit add(CreditModel model);
    Credit getById(Integer creditId);
    Map<String, ?> getCredit(LocalDate d1, LocalDate d2);
    //List getLoan(LocalDate d1, LocalDate d2);
    List<Map<String, ?>> getLoanByDay(LocalDate d1, LocalDate d2);
    List<Map<String, ?>> getLoan(LocalDate d1, LocalDate d2);

}
