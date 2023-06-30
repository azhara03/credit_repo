package com.Credit.credit.Service.Impl;
import com.Credit.credit.Entity.*;
import com.Credit.credit.Model.CreditModel;
import com.Credit.credit.Model.Platej;
import com.Credit.credit.Repository.CreditRepository;
import com.Credit.credit.Repository.ScheduleRepository;
import com.Credit.credit.Service.CreditService;
import com.Credit.credit.Service.CreditTermService;
import com.Credit.credit.Service.InterestRateService;
import com.Credit.credit.Service.TypeOfCreditService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditService {
    CreditRepository creditRepository;
    ScheduleRepository scheduleRepository;
    TypeOfCreditService typeOfCreditService;
    CreditTermService creditTermService;
    InterestRateService interestRateService;
    public List<Credit> findAll() {
        return (List<Credit>) creditRepository.findAll();
    }

    @Override
    public Credit getById(Integer creditId) {
        return creditRepository.findById(creditId).orElse(null);
    }

    private static boolean isWeekend(LocalDate date) {
        // Проверка, является ли день недели субботой или воскресеньем
        // Вернуть true, если день недели - выходной, иначе false
        return date.getDayOfWeek().getValue() >= 6;
    }
    public Credit add(CreditModel model) {
        try {
            Credit credit = new Credit(model.getSum(), model.getMonth(), model.getInterestRate(), model.getUser());
            InterestRate interestRate = interestRateService.getById(model.getInterestRate().getId());
            double yearPercent = interestRate.getPercent();
            double percentOneMonth = yearPercent / 12 / 100;
            int sumCredit = credit.getAmount();
            double k = (percentOneMonth * (Math.pow((1 + percentOneMonth), model.getMonth()))) / (Math.pow((1 + percentOneMonth), model.getMonth()) - 1);
            double total=k*sumCredit*model.getMonth();
            credit.setMonth_pay(k*sumCredit);
            credit.setTotal_debt(total);
            //Date date = new Date();
            creditRepository.save(credit);
            LocalDate date = credit.getStart_date();
            LocalDate dateOfPAy=date;
            Platej[] platejs = calculate(model);

            for (Platej platej : platejs) {
                Schedule schedule = new Schedule();

                if(platej.getMonth()==1)
                    dateOfPAy=date;
                else if(platej.getMonth()==1){
                    dateOfPAy = date.plusMonths(1);
                    /*if (isWeekend(dateOfPAy)) {
                        dateOfPAy = dateOfPAy.plusDays(1);
                        if (isWeekend(date))
                            dateOfPAy = dateOfPAy.plusDays(1);
                    }
                    schedule.setPayment_date(dateOfPAy);*/
                }
                else {
                    dateOfPAy = credit.getStart_date().plusMonths(platej.getMonth() - 1);
                    /*if (isWeekend(dateOfPAy)) {
                        dateOfPAy = dateOfPAy.plusDays(1);
                        if (isWeekend(date))
                            dateOfPAy = dateOfPAy.plusDays(1);
                    }
                    schedule.setPayment_date(dateOfPAy);*/
                    //schedule.setPayment_date(credit.getStart_date().plusMonths(platej.getMonth() - 1));
                }
                if (isWeekend(dateOfPAy)) {
                    dateOfPAy = dateOfPAy.plusDays(1);
                    if (isWeekend(dateOfPAy))
                        dateOfPAy = dateOfPAy.plusDays(1);
                }
                schedule.setPayment_date(dateOfPAy);
                schedule.setMonth_pay(platej.getPayForMonth());
                schedule.setMain_debt(platej.getMainDolg());
                schedule.setMonthly_percent(platej.getPercentOfMonthPay());
                schedule.setTotal_debt(platej.getOstatokOtPogasheniya());
                schedule.setCredit_id(credit.getId());
                scheduleRepository.save(schedule);
                //date=schedule.getPayment_date();
                /*if (isWeekend(date)) {
                    date = date.plusDays(1);
                    if (isWeekend(date))
                        date = date.plusDays(1);
                }*/
            }

            return credit;
        }
        catch (Exception ex){
            return null;
        }
    }
    public Platej[] calculate(CreditModel model) {
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
            if(a>0 && a<1 || a<0)
                a=0;
            pl[i].setOstatokOtPogasheniya(a);
        }
        return pl;
    }
}
