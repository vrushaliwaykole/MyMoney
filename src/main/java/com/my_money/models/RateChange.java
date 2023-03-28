package com.my_money.models;


import java.time.Month;

public class RateChange {
    private Month month;
    private float percentage;

    public RateChange(Month month, float percentage) {
        this.month = month;
        this.percentage = percentage;
    }

    public Month getMonth() {
        return month;
    }

    public float getPercentage() {
        return percentage;
    }
}
