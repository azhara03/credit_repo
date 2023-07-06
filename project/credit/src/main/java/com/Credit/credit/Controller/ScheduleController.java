package com.Credit.credit.Controller;

import com.Credit.credit.Entity.Credit;
import com.Credit.credit.Entity.Schedule;
import com.Credit.credit.Service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/all")
    public List<Schedule> getAll(){
        return scheduleService.findAll();
    }
    @GetMapping("/{id}")
    public Schedule getSchedule(@PathVariable Integer id){
        return scheduleService.findById(id);
    }
}
