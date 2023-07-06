package com.Credit.credit.Service.Impl;

import com.Credit.credit.Entity.InterestRate;
import com.Credit.credit.Repository.InterestRateRepository;
import com.Credit.credit.Service.InterestRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterestRateServiceImpl implements InterestRateService {
    @Autowired
    private InterestRateRepository interestRateRepository;
    @Override
    public InterestRate getById(Integer rateId) {
        return interestRateRepository.findById(rateId).orElse(null);
    }
}
