package com.Credit.credit.Controller;

import com.Credit.credit.Entity.Schedule;
import com.Credit.credit.Model.CreditModel;
import com.Credit.credit.Service.CreditService;
import com.Credit.credit.Service.ScheduleService;
import com.Credit.credit.Entity.Credit;
import com.Credit.credit.Model.Platej;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/credits")
public class CreditController {
   @Autowired
   private CreditService creditService;
    @Autowired
    private ScheduleService scheduleService;
    @GetMapping("/all")
    public List<Credit> getAll(){
        return creditService.findAll();
    }
    @GetMapping("/sch")
    public List<Schedule> getSchedule(@RequestParam(value="id_cr",required=false) Integer id){
        return scheduleService.getSchedule(id);
    }
    @GetMapping("/creditInfo")
    public Map<String, ?> getCreditInfo(@RequestParam(value="start_d",required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate d1,
                             @RequestParam(value="end_d",required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate d2){
        return creditService.getCredit(d1, d2);
    }
    @GetMapping("/loanInfo")
    public List<Map<String, ?>> getLoanInfo(@RequestParam(value="start_d",required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate d1,
                                                  @RequestParam(value="end_d",required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate d2){
        return creditService.getLoan(d1, d2);
    }
    @GetMapping("/loanByDay")
    public List<Map<String, ?>> getLoanByDay(@RequestParam(value="start_d",required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate d1,
                            @RequestParam(value="end_d",required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate d2){
        return creditService.getLoanByDay(d1, d2);
    }
    @GetMapping("/{id}")
    public Credit getCredit(@PathVariable Integer id){
        return creditService.getById(id);
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
