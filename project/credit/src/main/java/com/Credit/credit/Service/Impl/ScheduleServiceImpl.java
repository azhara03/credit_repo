package com.Credit.credit.Service.Impl;

import com.Credit.credit.Entity.Schedule;
import com.Credit.credit.Entity.TypeOfCredit;
import com.Credit.credit.Repository.ScheduleRepository;
import com.Credit.credit.Repository.TypeOfCreditRepository;
import com.Credit.credit.Service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Override
    public Schedule getById(Integer typeId) {
        return scheduleRepository.findById(typeId).orElse(null);
    }
}
