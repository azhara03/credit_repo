package com.Credit.credit.Service.Impl;
import com.Credit.credit.Entity.*;
import com.Credit.credit.Model.CreditModel;
import com.Credit.credit.Model.CreditTotal;
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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditService {
    UserRepository userRepository;
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
    @PersistenceContext
    private EntityManager em;
    @Override
    public Map<String, ?> getCredit(LocalDate d1, LocalDate d2){
        //StoredProcedureQuery query = em.createNamedStoredProcedureQuery("getAllSchedules");
        //query.registerStoredProcedureParameter("id_cr", Integer.class, ParameterMode.IN);
        /*query.setParameter("start_d", d1);
        query.setParameter("end_d", d2);
        query.execute();
        return query.getResultList();*/
        return creditRepository.getCreditInfo(d1, d2);
    }
    @Override
    public List getLoan(LocalDate d1, LocalDate d2){
        return creditRepository.getLoan(d1, d2);
    }
    @Override
    public List getLoanByDay(LocalDate d1, LocalDate d2){
        return creditRepository.getLoanByDay(d1, d2);
    }
    private static boolean isWeekend(LocalDate date) {
        // Проверка, является ли день недели субботой или воскресеньем
        // Вернуть true, если день недели - выходной, иначе false
        return date.getDayOfWeek().getValue() >= 6;
    }
    public Credit add(CreditModel model) {
        try {
            String code = LocalDate.now().toString().replace("-", "") + model.getUser().getId();;
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
            LocalDate date = credit.getStart_date().plusMonths(1);
            LocalDate startDate=date;
            if (isWeekend(date)) {
                date = date.plusDays(1);
                if (isWeekend(date))
                    date = date.plusDays(1);
            LocalDate dateOfPAy=date;

            Platej[] platejs = calculate(model);

            for (Platej platej : platejs) {
                Schedule schedule = new Schedule();

                if (platej.getMonth() == 1)
                    dateOfPAy = date;
                /*else if(platej.getMonth()==1){
                    dateOfPAy = date.plusMonths(1);
                }*/
                else {
                    dateOfPAy = startDate.plusMonths(platej.getMonth() - 1);
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
            }
            }
            User user = model.getUser();user.setPerconal_code(code);
            if (user.getFirst_name() == null) {    // Если first_name равно null, сохраняем имеющееся значение из базы данных
                User existingUser = userRepository.findById(user.getId()).orElse(null);    if (existingUser != null) {
                    user.setFirst_name(existingUser.getFirst_name());    }
            }
//чтобы сохранить только personal_code используем saveAndflushuserRepository.saveAndFlush(user);
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
