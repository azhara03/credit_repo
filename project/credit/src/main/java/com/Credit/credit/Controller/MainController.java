package com.Credit.credit.Controller;


import com.Credit.credit.Entity.CreditTerm1;
import com.Credit.credit.Entity.TypeOfCredit;
import com.Credit.credit.Entity.currencyE;
import com.Credit.credit.Service.CreditTermService;
import com.Credit.credit.Service.CurrencyService;
import com.Credit.credit.Service.TypeOfCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private TypeOfCreditService typeOfCreditService;
    @Autowired
    private CreditTermService creditTermService;
    @GetMapping("/")
    public String Main(Model model){
        Iterable<currencyE> cs=currencyService.findAll();
        Iterable<TypeOfCredit> tc=typeOfCreditService.findAll();
        Iterable<CreditTerm1> ct=creditTermService.findAll();
       // Iterable<CreditTerm> ct=creditTermService.findAll();
        model.addAttribute("currency", cs);
        model.addAttribute("type", tc);
        model.addAttribute("term", ct);
        //model.addAttribute("editMode",true);
        return "Main";
    }

    /*public String getNum(Model model){
        //return num;
    }*/
}
