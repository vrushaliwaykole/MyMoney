package com.my_money.models;

import java.time.Month;

public class Balance {
    private Month month;
    private double amount;

    public Balance(Month month, double amount) {
        this.month = month;
        this.amount = amount;
    }

    public Month getMonth() {
        return month;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double newAmount) {
        this.amount = newAmount;
    }
}
