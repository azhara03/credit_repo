package com.Credit.credit.Repository;

import com.Credit.credit.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Map;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    @Query(value = "select * from  fnc_payment_report(:start_d, :end_d)", nativeQuery = true)
    Map<String, ?> getPaymentInfo(@Param("start_d") LocalDate date1, @Param("end_d") LocalDate date2);

    @Query(value = "SELECT sum(total_amount) " +
            "    FROM payments" +
            "    WHERE payment_date Between ?1 and ?2" +
            "    ", nativeQuery = true)
    Double sum(LocalDate date1,LocalDate date2);
}
