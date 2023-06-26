package com.Credit.credit.Service.Impl;

import com.Credit.credit.Entity.CreditTerm1;
import com.Credit.credit.Repository.CreditTermRepository;
import com.Credit.credit.Service.CreditTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditTermServiceImpl implements CreditTermService {
    @Autowired
    private CreditTermRepository creditTermRepository;
    //@Override
    public List<CreditTerm1> findAll() {
        return creditTermRepository.findAll();
    }

    @Override
    public CreditTerm1 getById(Integer id) {
        return creditTermRepository.findById(id).orElse(null);
    }
}
