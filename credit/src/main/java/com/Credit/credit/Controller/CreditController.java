package com.Credit.credit.Controller;

import com.Credit.credit.Entity.Credit;
import com.Credit.credit.Model.CreditModel;
import com.Credit.credit.Model.Platej;
import com.Credit.credit.Service.CreditService;
import com.Credit.credit.Service.CreditTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@RestController
@RequestMapping("/credits")
public class CreditController {
    @Autowired
    private CreditService creditService;
    @GetMapping("/all")
    public List<Credit> getAll(){
        return creditService.findAll();
    }
    @GetMapping("/calculate")
    public Platej[] Main(@RequestBody CreditModel model){
        return creditService.calculate(model);
    }
    @PostMapping("/add")
    public CreditModel add(@RequestBody CreditModel model){
        creditService.add(model);
        return model;
    }

}
/**
 {
 "amount":50000,
 "typeid":{
 "id":1
 },
 "currencyid":{
 "id":1
 },
 "term":{
 "id":2
 }
 }
 *
 */
