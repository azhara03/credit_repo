package com.Credit.credit.Service;

import com.Credit.credit.Entity.Credit;
import com.Credit.credit.Entity.Schedule;

import java.util.List;

public interface ScheduleService {
    Schedule getById(Integer creditId);
    List<Schedule> getSchedule(Integer creditId);
}
