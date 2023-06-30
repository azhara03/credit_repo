package com.Credit.credit.Entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "payments")
public class Payment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column
    private int schedule_id;
    @Column
    private LocalDate payment_date;
    @Column
    private double delay_sum;
    @Column
    private double total_amount;

    public Payment() {

    }

    public Payment(Schedule schedule, double totalAmount) {
        this.schedule_id=schedule.getId();
        this.total_amount=totalAmount;
    }


    /*public Payment(Schedule schedule, LocalDate payment_date, int delay_sum, double total_amount) {
        LocalDate now = LocalDate.now();

        if(isWeekend(now))
        {
            now=now.plusDays(1);
            if(isWeekend(now))
                now=now.plusDays(1);
        }
        this.schedule_id = schedule.getId();
        this.payment_date = now;
        this.delay_sum = delay_sum;
        this.total_amount = total_amount;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(int schedule_id) {
        this.schedule_id = schedule_id;
    }

    public LocalDate getPayment_date() {
        return payment_date;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public void setPayment_date(LocalDate payment_date) {
        this.payment_date = payment_date;
    }

    public double getDelay_sum() {
        return delay_sum;
    }

    public void setDelay_sum(double delay_sum) {
        this.delay_sum = delay_sum;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    @ManyToOne(optional=false)
    @JoinColumn(name="schedule_id", insertable = false, updatable = false)
    private Schedule schedule;
}
