package com.Credit.credit.Model;

public class Platej {
    private int month;
    private double payForMonth;
    private double percentOfMonthPay;
    private double mainDolg;
    private double ostatokOtPogasheniya;
    public Platej() {
    }

    public Platej(int month, double payForMonth, double percentOfMonthPay, double mainDolg, double ostatokOtPogasheniya) {
        this.month = month;
        this.payForMonth = payForMonth;
        this.percentOfMonthPay = percentOfMonthPay;
        this.mainDolg = mainDolg;
        this.ostatokOtPogasheniya = ostatokOtPogasheniya;
    }

    public int getMonth() {
        return month;
    }
    public double getPayForMonth() {
        return payForMonth;
    }

    public double getPercentOfMonthPay() {
        return percentOfMonthPay;
    }

    public double getMainDolg() {
        return mainDolg;
    }

    public double getOstatokOtPogasheniya() {
        return ostatokOtPogasheniya;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setPayForMonth(double payForMonth) {
        this.payForMonth = payForMonth;
    }

    public void setPercentOfMonthPay(double percentOfMonthPay) {
        this.percentOfMonthPay = percentOfMonthPay;
    }

    public void setMainDolg(double mainDolg) {
        this.mainDolg = mainDolg;
    }

    public void setOstatokOtPogasheniya(double ostatokOtPogasheniya) {
        this.ostatokOtPogasheniya = ostatokOtPogasheniya;
    }
}
