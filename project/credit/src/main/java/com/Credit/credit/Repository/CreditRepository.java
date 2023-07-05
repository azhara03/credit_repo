package com.Credit.credit.Repository;

import com.Credit.credit.Entity.Credit;
import com.Credit.credit.Entity.Schedule;
import com.Credit.credit.Model.CreditTotal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface CreditRepository extends JpaRepository<Credit, Integer> {
    /*@Procedure(procedureName = "getCred")
    Map<String, ?> getCreditInfo(@Param("start_d") LocalDate date1, @Param("end_d") LocalDate date2);*/
    @Query(value = "select * from  fnc_credit_report(:start_d, :end_d)", nativeQuery = true)
    Map<String, ?> getCreditInfo(@Param("start_d") LocalDate date1, @Param("end_d") LocalDate date2);

    @Query(value = "select * from  fnc_schedule_report(:start_d, :end_d)", nativeQuery = true)
    List getLoan(@Param("start_d") LocalDate date1, @Param("end_d") LocalDate date2);
    @Query(value = "select * from  fnc_schedule_day_report(:start_d, :end_d)", nativeQuery = true)
    List getLoanByDay(@Param("start_d") LocalDate date1, @Param("end_d") LocalDate date2);

    /*@Procedure(name = "Credit.getCredits")
    List getLoan(@Param("start_d") LocalDate date1, @Param("end_d") LocalDate date2);*/

}
