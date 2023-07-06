package com.Credit.credit.Service;

import com.Credit.credit.Entity.Credit;
import com.Credit.credit.Entity.Schedule;

import java.util.List;

public interface ScheduleService {
    List<Schedule> findAll();
    Schedule getById(Integer creditId);
    Schedule findById(Integer scheduleId);
    List<Schedule> getSchedule(Integer creditId);
}
