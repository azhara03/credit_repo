package com.Credit.credit.Entity;

import com.Credit.credit.Model.CreditTotal;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@SqlResultSetMapping(
        name="getCred", classes = {
                @ConstructorResult(targetClass = CreditTotal.class, columns = {
                        @ColumnResult(name="month"),
                        @ColumnResult(name="count"),
                        @ColumnResult(name="sum")
                }),
}
)
@NamedStoredProcedureQuery(name = "Credit.getCredits",
        procedureName = "fnc_schedule_report", resultSetMappings = {"getCred"},
        parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "start_d", type = LocalDate.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "end_d", type = LocalDate.class)}
)
@Table(name = "credit")
public class Credit {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column
    private int amount;
    @Column
    private int term;
    @Column
    private int percent_id;
    @Column
    private LocalDate start_date;
    @Column
    private LocalDate end_date;
    @Column
    private int client_id;
    @Column
    //кол-во просрочек
    private int delay_amount;
    @Column
    //основ долг
    private double total_debt;
    @Column
    private boolean repaid_status;
    @Column
    private double monthpay;

    public Credit(int sum, InterestRate interestRate, int term) {
        this.amount = sum;
        this.percent_id = interestRate.getId();
        this.term=term;
    }

    public Credit() {
    }
    /*private static boolean isHoliday(LocalDate date, HolidayManager holidayManager) {
        Holiday holiday = holidayManager.getHoliday(date);
        return holiday != null;
    }*/
    private static boolean isWeekend(LocalDate date) {
        // Проверка, является ли день недели субботой или воскресеньем
        // Вернуть true, если день недели - выходной, иначе false
        return date.getDayOfWeek().getValue() >= 6;
    }
    public Credit(int sum, int month, InterestRate interestRate, User user) {
        LocalDate now = LocalDate.now();
        /*HolidayManager holidayManager = HolidayManager.getInstance(HolidayCalendar.RUSSIA); // Установка календаря праздников (Россия)
        LocalDate paymentDate = getNextPaymentDate(currentDate, holidayManager);
        currentDate = paymentDate.plusMonths(1);*/
        if(isWeekend(now))
        {
            now=now.plusDays(1);
            if(isWeekend(now))
                now=now.plusDays(1);
        }

        this.amount = sum;
        this.term=month;
        LocalDate d=LocalDate.now();
        d=d.plusMonths(month);

        this.percent_id = interestRate.getId();
        //InterestRate r=new InterestRate(interestRate.getId());
        this.client_id=user.getId();
        this.start_date=now;
        if(isWeekend(d)) {
            d=d.plusDays(1);
            if(isWeekend(d))
                d=d.plusDays(1);
        }
        this.end_date=d;
        this.delay_amount=0;

        //this.total_debt=;
        this.repaid_status=false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate date) {
        //LocalDate now = LocalDate.now();
        this.start_date = date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getDelay_amount() {
        return delay_amount;
    }

    public void setDelay_amount(int delay_amount) {
        this.delay_amount = delay_amount;
    }

    public double getTotal_debt() {
        return total_debt;
    }

    public void setTotal_debt(double main_debt) {
        this.total_debt = main_debt;
    }


    public InterestRate getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(InterestRate interestRate) {
        this.interestRate = interestRate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public int getPercent_id() {
        return percent_id;
    }

    public void setPercent_id(int percent_id) {
        this.percent_id = percent_id;
    }

    public boolean isRepaid_status() {
        return repaid_status;
    }

    public void setRepaid_status(boolean repaid_status) {
        this.repaid_status = repaid_status;
    }

    public double getMonth_pay() {
        return monthpay;
    }

    public void setMonth_pay(double month_pay) {
        this.monthpay = month_pay;
    }

    @ManyToOne(optional=false)
    @JoinColumn(name="percent_id", insertable = false, updatable = false)
    private InterestRate interestRate;

    @ManyToOne(optional=false)
    @JoinColumn(name="client_id", insertable = false, updatable = false)
    private User user;

}
