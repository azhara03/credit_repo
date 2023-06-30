package com.Credit.credit.Service.Impl;

import com.Credit.credit.Entity.*;
import com.Credit.credit.Model.PaymentModel;
import com.Credit.credit.Repository.CreditRepository;
import com.Credit.credit.Repository.PaymentRepository;
import com.Credit.credit.Repository.ScheduleRepository;
import com.Credit.credit.Service.PaymentService;
import com.Credit.credit.Service.ScheduleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    CreditRepository creditRepository;
    ScheduleService scheduleService;
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }
    LocalDate compare(LocalDate dateOfSession, LocalDate compared)
    {
        long resultDays = ChronoUnit.DAYS.between(compared, dateOfSession);
        return LocalDate.of(0, 1, 1).plusDays(resultDays - 1);
    }
    private static boolean isWeekend(LocalDate date) {
        // Проверка, является ли день недели субботой или воскресеньем
        // Вернуть true, если день недели - выходной, иначе false
        return date.getDayOfWeek().getValue() >= 6;
    }
    public Payment add(PaymentModel model){

        Payment payment=new Payment(model.getSchedule(), model.getTotal_amount());
        Schedule schedule = scheduleService.getById(model.getSchedule().getId());
        if(!schedule.isPayment_status()) {
            LocalDate date = schedule.getPayment_date();//дата по графику
            LocalDate now = LocalDate.now();

            if (isWeekend(now)) {
                now = now.plusDays(1);
                if (isWeekend(now))
                    now = now.plusDays(1);
            }
            Credit credit = new Credit();
            credit = creditRepository.findById(schedule.getCredit_id()).get();
            LocalDate date1 = now; //дата платежа
            long a = date1.toEpochDay() - date.toEpochDay();
            double summa_dolga = schedule.getCredit().getTotal_debt();

            if (model.getTotal_amount() >= credit.getMonth_pay() && model.getTotal_amount() <= summa_dolga) {
                double peni = 0;
                if (a == 0) {
                    //если клиент весь долг заплатил
                    if(model.getTotal_amount()==credit.getTotal_debt()){
                        credit.setTotal_debt(0);
                        credit.setRepaid_status(true);
                    }
                    else {
                        credit.setTotal_debt(credit.getTotal_debt() - model.getTotal_amount());
                    }
                    ///schedule.setPayment(model.getTotal_amount());
                }
                else {
                    double percent = schedule.getCredit().getInterestRate().getDelay_percent();
                    peni = schedule.getMonth_pay() * (percent / 100) * a;
                    payment.setDelay_sum(peni);
                    //если клиент весь долг заплатил включая пени
                    if(model.getTotal_amount()-peni==credit.getTotal_debt()){
                        credit.setTotal_debt(0);
                        credit.setRepaid_status(true);
                    }
                    else {
                        credit.setTotal_debt(credit.getTotal_debt() - (model.getTotal_amount() - peni));
                    }
                    //schedule.setPayment(model.getTotal_amount()-peni);
                }
                if(model.getTotal_amount() > credit.getMonth_pay()) {
                    Period p = Period.between(date1, credit.getStart_date());
                    //кол-во оставшихся месяцев
                    int month = credit.getTerm() - (p.getMonths() + 1);
                    double ejemPlatejPosleDosrPogash = (credit.getTotal_debt() / month);
                    credit.setMonth_pay(ejemPlatejPosleDosrPogash);
                }
                payment.setPayment_date(now);
                schedule.setPayment_status(true);
                creditRepository.save(credit);
                paymentRepository.save(payment);
            }

        }
        return payment;
    }
}
