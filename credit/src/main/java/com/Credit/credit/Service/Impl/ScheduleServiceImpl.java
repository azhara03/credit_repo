/*package com.Credit.credit.Service.Impl;
import com.Credit.credit.Entity.Credit;
import com.Credit.credit.Entity.InterestRate;
import com.Credit.credit.Entity.Schedule;
import com.Credit.credit.Model.CreditModel;
import com.Credit.credit.Model.Platej;
import com.Credit.credit.Repository.CreditRepository;
import com.Credit.credit.Repository.ScheduleRepository;
import com.Credit.credit.Service.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
//import javax.ws.rs.InternalServerErrorException;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    ScheduleRepository scheduleRepository;
    CreditRepository creditRepository;
    TypeOfCreditService typeOfCreditService;
    CreditTermService creditTermService;
    InterestRateService interestRateService;

    private static boolean isWeekend(LocalDate date) {
        // Проверка, является ли день недели субботой или воскресеньем
        // Вернуть true, если день недели - выходной, иначе false
        return date.getDayOfWeek().getValue() >= 6;
    }
    public Credit add(CreditModel model) {
        Credit credit = new Credit(model.getSum(), model.getMonth(), model.getInterestRate(), model.getUser());
        creditRepository.save(credit);

        LocalDate date = model.getStart_date();
        Platej[] platejs = calculatePlatej(model);
        for (Platej platej : platejs) {
            Schedule schedule = new Schedule();
            if (isWeekend(date)) {
                date = date.plusDays(1);
                if (isWeekend(date))
                    date = date.plusDays(1);
            }
            schedule.setPayment_date(date);

            schedule.setMonth_pay(platej.getPayForMonth());

            schedule.setCredit_id(credit.getId());
            scheduleRepository.save(schedule);
            date.plusMonths(1);
        }
        return credit;
    }
        public Platej[] calculatePlatej(CreditModel model) {
        Credit credit = new Credit(model.getSum(), model.getInterestRate(), model.getMonth());
        InterestRate interestRate = interestRateService.getById(model.getInterestRate().getId());
        //CreditTerm1 creditTerm1 = creditTermService.getById(model.getCreditTerm1().getId());
        double k = 0, a=0;
        double yearPercent = interestRate.getPercent();
        double countMonth = credit.getTerm();
        double percentOneMonth = yearPercent / 12 / 100;
        int sumCredit = credit.getAmount();
        k = (percentOneMonth * (Math.pow((1 + percentOneMonth), countMonth))) / (Math.pow((1 + percentOneMonth), countMonth) - 1);
        Platej[] pl=new Platej[(int)countMonth];
        for(int i=0;i<countMonth; i++){
            pl[i]=new Platej();
            pl[i].setMonth(i+1);
            pl[i].setPayForMonth(k*sumCredit);
            if(i==0) {
                pl[i].setPercentOfMonthPay((yearPercent/12/100)*sumCredit);//
                pl[i].setMainDolg(pl[i].getPayForMonth()-pl[i].getPercentOfMonthPay());
                a = sumCredit - pl[i].getMainDolg();
            }
            else {
                pl[i].setPercentOfMonthPay((yearPercent/12/100)*pl[i-1].getOstatokOtPogasheniya());//
                pl[i].setMainDolg(pl[i].getPayForMonth()-pl[i].getPercentOfMonthPay());
                a = pl[i - 1].getOstatokOtPogasheniya() - pl[i].getMainDolg();
            }
            pl[i].setOstatokOtPogasheniya(a);
        }
        return pl;
        //return k * sumCredit;
    }




}*/