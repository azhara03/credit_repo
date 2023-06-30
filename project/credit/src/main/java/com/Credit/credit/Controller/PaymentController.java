package com.Credit.credit.Controller;

import com.Credit.credit.Model.CreditModel;
import com.Credit.credit.Model.PaymentModel;
import com.Credit.credit.Service.CreditService;
import com.Credit.credit.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @PostMapping("/add")
    public PaymentModel add(@RequestBody PaymentModel model){

        paymentService.add(model);
        return model;
    }
}
