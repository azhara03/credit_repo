package com.Credit.credit.Service;

import com.Credit.credit.Entity.Payment;
import com.Credit.credit.Model.PaymentModel;

import java.util.List;

public interface PaymentService {
    List<Payment> findAll();
    Payment add(PaymentModel paymentModel);
}
