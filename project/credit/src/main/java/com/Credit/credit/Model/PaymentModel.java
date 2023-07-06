package com.Credit.credit.Model;

import com.Credit.credit.Entity.Schedule;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class PaymentModel {
    private int id;
    private Schedule schedule;
    private LocalDate payment_date;
    private double delay_sum;
    private double total_amount;

    public PaymentModel(Schedule schedule, double total_amount) {
        this.schedule = schedule;
        this.total_amount = total_amount;
    }
}
