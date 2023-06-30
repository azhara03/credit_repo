package com.Credit.credit.Service.Impl;
import com.Credit.credit.Entity.*;
import com.Credit.credit.Model.CreditModel;
import com.Credit.credit.Model.Platej;
import com.Credit.credit.Repository.CreditRepository;
import com.Credit.credit.Repository.ScheduleRepository;
import com.Credit.credit.Repository.UserRepository;
import com.Credit.credit.Service.CreditService;
import com.Credit.credit.Service.CreditTermService;
import com.Credit.credit.Service.InterestRateService;
import com.Credit.credit.Service.TypeOfCreditService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
//import javax.ws.rs.InternalServerErrorException;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditService {
    CreditRepository creditRepository;
    ScheduleRepository scheduleRepository;
    UserRepository userRepository;
    TypeOfCreditService typeOfCreditService;
    CreditTermService creditTermService;
    InterestRateService interestRateService;
    private static boolean isWeekend(LocalDate date) {
        // Проверка, является ли день недели субботой или воскресеньем
        // Вернуть true, если день недели - выходной, иначе false
        return date.getDayOfWeek().getValue() >= 6;
    }
    public List<Credit> findAll() {
        return (List<Credit>) creditRepository.findAll();
    }

    /*public double calculate(CreditModel model) {
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
    }*/
    public Credit add(CreditModel model) {
        try {
             String code = LocalDate.now().toString().replace("-", "") + model.getUser().getId();
            Credit credit = new Credit(model.getSum(), model.getMonth(), model.getInterestRate(), model.getUser());
         // credit.getUser().setPerconal_code(code);

            InterestRate interestRate = interestRateService.getById(model.getInterestRate().getId());
            double yearPercent = interestRate.getPercent();
            double percentOneMonth = yearPercent / 12 / 100;
            int sumCredit = credit.getAmount();
            double k = (percentOneMonth * (Math.pow((1 + percentOneMonth), model.getMonth()))) / (Math.pow((1 + percentOneMonth), model.getMonth()) - 1);
            double total=k*sumCredit*model.getMonth();
            credit.setMain_debt(total);
            //credit.setMonthpay();
            creditRepository.save(credit);
            LocalDate date = credit.getStart_date();
            Platej[] platejs = calculate(model);
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
                date=date.plusMonths(1);
            }
            User user = model.getUser();
            user.setPerconal_code(code);
            if (user.getFirst_name() == null) {
                // Если first_name равно null, сохраняем имеющееся значение из базы данных
                User existingUser = userRepository.findById(user.getId()).orElse(null);
                if (existingUser != null) {
                    user.setFirst_name(existingUser.getFirst_name());
                }
            }

            //чтобы сохранить только personal_code используем saveAndflush
            userRepository.saveAndFlush(user);

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
            pl[i].setOstatokOtPogasheniya(a);
        }
        return pl;
        //return k * sumCredit;
    }
}
