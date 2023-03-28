package com.my_money.models;

import java.time.Month;
import java.util.ArrayList;
import java.util.Optional;

public class MutualFund {
    private MutualFundType type; //
    private ArrayList<RateChange> rateChanges = new ArrayList<>();

    public MutualFund(MutualFundType type) {
        this.type = type;
    }

    public void addRateChange(String month, float percentage) {
        this.rateChanges.add(new RateChange(Month.valueOf(month), percentage));
    }

    public MutualFundType getType() {
        return type;
    }

    public Optional<Float> getRateChange(Month month) {
        return this.rateChanges.stream().filter(rateChange -> rateChange.getMonth() == month).findFirst().map(RateChange::getPercentage);
    }
}
