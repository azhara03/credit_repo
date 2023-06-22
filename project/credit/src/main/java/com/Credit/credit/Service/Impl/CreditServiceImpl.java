package com.Credit.credit.Service.Impl;

import com.Credit.credit.Entity.Credit;
import com.Credit.credit.Entity.CreditTerm1;
import com.Credit.credit.Entity.TypeOfCredit;
import com.Credit.credit.Model.CreditModel;
import com.Credit.credit.Repository.CreditRepository;
import com.Credit.credit.Repository.CreditTermRepository;
import com.Credit.credit.Repository.TypeOfCreditRepository;
import com.Credit.credit.Service.CreditService;
import com.Credit.credit.Service.CreditTermService;
import com.Credit.credit.Service.TypeOfCreditService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditService {
    CreditRepository creditRepository;
    TypeOfCreditService typeOfCreditService;
    CreditTermService creditTermService;

    public List<Credit> findAll() {
        return (List<Credit>) creditRepository.findAll();
    }

    public double calculate(CreditModel model) {
        Credit credit = new Credit(model.getSum(), model.getType(), model.getCurrencyE(), model.getCreditTerm1());
        TypeOfCredit type = typeOfCreditService.getById(model.getType().getId());
        CreditTerm1 creditTerm1 = creditTermService.getById(model.getCreditTerm1().getId());
        double k = 0;
        double yearPercent = type.getPercent();
        double countMonth = creditTerm1.getTerm();
        double percentOneMonth = yearPercent / 12 / 100;
        int sumCredit = credit.getAmount();
        k = (percentOneMonth * (Math.pow((1 + percentOneMonth), countMonth))) / (Math.pow((1 + percentOneMonth), countMonth) - 1);
        return k * sumCredit;
    }
}
