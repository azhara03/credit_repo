package com.Credit.credit.Repository;

import com.Credit.credit.Entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    @Query(value = "select * from  sp_schedules7(:id_cr)", nativeQuery = true)
    List<Schedule> getSchedule(@Param("id_cr") Integer creditId);

    /*@Procedure(procedureName = "getAllSchedules")
    List<Schedule> getSchedule(@Param("id_cr") Integer creditId);*/

}
