package com.Credit.credit.Service.Impl;

import com.Credit.credit.Entity.TypeOfCredit;
import com.Credit.credit.Repository.TypeOfCreditRepository;
import com.Credit.credit.Service.TypeOfCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TypeOfCreditServiceImpl implements TypeOfCreditService {
    @Autowired
    private TypeOfCreditRepository typeOfCreditRepository;
    @Override
    public List<TypeOfCredit> findAll() {
        return typeOfCreditRepository.findAll();
    }

    @Override
    public TypeOfCredit getById(Integer typeId) {
        return typeOfCreditRepository.findById(typeId).orElse(null);
    }
}
