package com.Credit.credit.Service;

import com.Credit.credit.Entity.Payment;
import com.Credit.credit.Model.PaymentModel;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface PaymentService {
    List<Payment> findAll();
    Payment add(PaymentModel paymentModel);
    Map<String, ?> getPayment(LocalDate d1, LocalDate d2);
    Double summ (LocalDate d1, LocalDate d2);
}
