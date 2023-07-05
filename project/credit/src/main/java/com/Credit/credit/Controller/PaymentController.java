package com.Credit.credit.Controller;

import com.Credit.credit.Model.CreditModel;
import com.Credit.credit.Model.PaymentModel;
import com.Credit.credit.Service.CreditService;
import com.Credit.credit.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

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
    @GetMapping("/paymentInfo")
    public Map<String, ?> getCreditInfo(@RequestParam(value="start_d",required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate d1,
                                        @RequestParam(value="end_d",required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate d2){
        return paymentService.getPayment(d1, d2);
    }
    @GetMapping("/sum")
    public Double getInfo(@RequestParam(value="start_d",required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate d1,
                                        @RequestParam(value="end_d",required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate d2){
        return paymentService.summ(d1, d2);
    }
}
