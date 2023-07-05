package com.Credit.credit.Repository;

import com.Credit.credit.Entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    @Query(value = "select * from  sp_schedules7(:id_cr)", nativeQuery = true)
    List<Schedule> getSchedule(@Param("id_cr") Integer creditId);

    /*@Procedure(procedureName = "getAllSchedules")
    List<Schedule> getSchedule(@Param("id_cr") Integer creditId);*/

}
